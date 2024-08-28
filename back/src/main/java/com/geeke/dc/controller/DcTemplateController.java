package com.geeke.dc.controller;

import com.alibaba.fastjson.JSONObject;
import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.dc.entity.DcTemplate;
import com.geeke.dc.service.DcTemplateService;
import com.geeke.sys.controller.BaseController;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 模板管理Controller
 * @author
 * @version
 */
@RestController
@RequestMapping(value = "/dc/dcTemplate")
public class DcTemplateController extends BaseController {

    @Autowired
    private DcTemplateService dcTemplateService;

    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        DcTemplate entity = dcTemplateService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<DcTemplate> result = dcTemplateService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<DcTemplate> result = dcTemplateService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(
        @RequestParam(value = "entity", required = false) String strEntity,
        @RequestParam(value = "fileIdUploads", required = false) MultipartFile[] fileIdUploads, // 文件: 详情图
        @RequestParam(value = "deleteIds", required = false) String strDeleteIds
    ) throws java.io.IOException {
        DcTemplate entity = JSONObject.parseObject(strEntity, DcTemplate.class);
        String[] deleteIds = JSONObject.parseObject(strDeleteIds, String[].class);
        String id = dcTemplateService.save(entity, fileIdUploads, deleteIds).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody DcTemplate entity) {
        int rows = dcTemplateService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody List<DcTemplate> entitys) {
        List<String> ids = dcTemplateService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody List<DcTemplate> entitys) {
        List<String> ids = dcTemplateService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }

    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody List<DcTemplate> entitys) {
        int rows = dcTemplateService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }

    @PostMapping(value = "listTemplateAndFile")
    public ResponseEntity<JSONObject> listTemplateAndFile(@RequestBody SearchParams searchParams) {
        List<DcTemplate> result = dcTemplateService.listTemplateAndFile(searchParams.getParams(), searchParams.getOrderby());
        return ResponseEntity.ok(ResultUtil.successJson(result));
    }
}
