package com.geeke.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.dbtype.entity.SysDbType;
import com.geeke.project.annotation.DataSourceChange;
import com.geeke.project.entity.Project;
import com.geeke.project.service.ProjectService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.JdbcUtils;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目管理Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/project/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        Project entity = projectService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<Project> result = projectService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<Project> result = projectService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @DataSourceChange
    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody Project entity) {
        String id = projectService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @DataSourceChange(remove = true)
    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody Project entity) {
        int rows = projectService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<Project> entitys) {
        List<String> ids = projectService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<Project> entitys) {
        List<String> ids = projectService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<Project> entitys) {
        int rows = projectService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "linkJdbcConnection")
    public ResponseEntity<JSONObject> linkJdbcConnection(@RequestBody Project entity) {
        String username = entity.getDbUsername();
        String password = entity.getDbPassword();
        String url = entity.getDbUrl();
        SysDbType dbType = entity.getDbType();
        Objects.requireNonNull(dbType, "项目获取不到相关数据库类型!");
        String driver = dbType.getDriver();
        if (
            StringUtils.isEmpty(username) ||
            StringUtils.isEmpty(password) ||
            StringUtils.isEmpty(url) ||
            StringUtils.isEmpty(driver)
        ) {
            throw new CommonJsonException(ErrorEnum.E_90005);
        }
        JdbcUtils jdbcUtils = new JdbcUtils(username, password, url, driver);
        Connection connection = jdbcUtils.getConnection();
        Objects.requireNonNull(connection, "获取不到连接!");
        //可以关闭了 不能造成资源浪费
        jdbcUtils.close(connection);
        return ResponseEntity.ok(ResultUtil.successJson());
    }

    @GetMapping(value = "checkDataSource/{projectId}")
    public ResponseEntity<JSONObject> checkDataSource(@PathVariable("projectId") String projectId) {
        Project project = projectService.get(projectId);
        String username = project.getDbUsername();
        String password = project.getDbPassword();
        String url = project.getDbUrl();
        SysDbType dbType = project.getDbType();
        Objects.requireNonNull(dbType, "项目获取不到相关数据库类型!");
        String driver = dbType.getDriver();
        if (
            StringUtils.isNotEmpty(username) &&
            StringUtils.isNotEmpty(password) &&
            StringUtils.isNotEmpty(url) &&
            StringUtils.isNotEmpty(driver)
        ) {
            JdbcUtils jdbcUtils = new JdbcUtils(username, password, url, driver);
            Connection connection = jdbcUtils.getConnection();
            Objects.requireNonNull(connection, "获取不到连接!");
            //可以关闭了 不要造成资源浪费
            jdbcUtils.close(connection);
            return ResponseEntity.ok(ResultUtil.successJson());
        } else {
            throw new CommonJsonException(ErrorEnum.E_90006);
        }
    }

    @PostMapping(value = "listProjectTableColumn")
    public ResponseEntity<JSONObject> listProjectTableColumn(@RequestBody SearchParams searchParams) {
        List<Project> result = projectService.listProjectTableColumn(searchParams.getParams(), searchParams.getOrderby());
        return ResponseEntity.ok(ResultUtil.successJson(result));
    }
}
