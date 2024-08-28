package com.geeke.project.service;

import com.geeke.common.constants.ActionConstants;
import com.geeke.common.data.Page;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.dc.dao.DcClienSchemeDao;
import com.geeke.dc.dao.DcSchemeDao;
import com.geeke.dc.dao.DcTableColumnDao;
import com.geeke.dc.dao.DcTableDao;
import com.geeke.dc.entity.DcClienScheme;
import com.geeke.project.dao.MemberDao;
import com.geeke.project.dao.ProjectDao;
import com.geeke.project.entity.Member;
import com.geeke.project.entity.Project;
import com.geeke.project.service.MemberService;
import com.geeke.sys.entity.Action;
import com.geeke.sys.entity.ActionRecycle;
import com.geeke.utils.Reflections;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目管理Service
 * @author
 * @version
 */

@Service("projectService")
@Transactional(readOnly = true)
public class ProjectService extends CrudService<ProjectDao, Project> {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DcSchemeDao dcSchemeDao;
    @Autowired
    private DcTableDao dcTableDao;
    @Autowired
    private DcTableColumnDao dcTableColumnDao;
    @Autowired
    private DcClienSchemeDao dcClienSchemeDao;

    @Override
    public Project get(String id) {
        Project project = super.get(id);

        List<Parameter> params = null;
        PageRequest pageRequest;

        /*获取子表列表   项目成员*/
        params = Lists.newArrayList();

        params.add(new Parameter("project_id", "=", project.getId()));
        pageRequest = new PageRequest(params);
        project.setMemberList(memberDao.listAll(pageRequest));

        return project;
    }

    @Transactional(readOnly = false)
    @Override
    public Project save(Project project) {
        Boolean addFlag = false;
        if (StringUtils.isBlank(project.getId())) {
            addFlag = true;
        }
        Project projectTemp = super.save(project);

        /* 保存子表数据     项目成员 */
        saveMemberList(projectTemp);

        if (addFlag) {
            /* 保存基础方案 */
            saveScheme(projectTemp);
        }

        return projectTemp;
    }

    /**
     * 删除
     * @param project
     */
    @Override
    @Transactional(readOnly = false)
    public int delete(Project project) {
        List<Parameter> params = null;
        PageRequest pageRequest;

        /* 处理子表     项目成员 */
        params = Lists.newArrayList();

        params.add(new Parameter("project_id", "=", project.getId()));
        pageRequest = new PageRequest(params);
        project.setMemberList(memberDao.listAll(pageRequest));

        if (project.getMemberList() != null && project.getMemberList().size() > 0) {
            memberService.bulkDelete(project.getMemberList());
        }

        /* 同时删除方案等 */
        deleteScheme(project);

        int rows = super.delete(project);
        return rows;
    }

    /**
     * 批量删除
     * @param projectList
     */
    @Override
    @Transactional(readOnly = false)
    public int bulkDelete(List<Project> projectList) {
        for (Project project : projectList) {
            delete(project);
        }

        return projectList.size();
    }

    /**
     * 生成操作日志
     * @param actionTypeId  操作类型Id
     * @param entity        操作的实体对象
     * @return
     */
    @Override
    protected Action createAction(String actionTypeId, Project entity) {
        Action action = super.createAction(actionTypeId, entity);
        if (action == null) {
            return null;
        }
        // 删除时记录把保存的数据保存到回收站
        if (ActionConstants.ACTION_DELETED.equals(actionTypeId)) {
            for (Member child : entity.getMemberList()) {
                ActionRecycle recycle = new ActionRecycle();
                recycle.setTableName(child.getBusTableName());
                recycle.setObjectId(child.getId());
                recycle.setObjectName((String) Reflections.invokeGetter(child, "name"));
                action.getActionRecycleList().add(recycle);
            }
        }
        return action;
    }

    /* 保存子表数据     项目成员 */
    private void saveMemberList(Project project) {
        List<Parameter> params = Lists.newArrayList();

        params.add(new Parameter("project_id", "=", project.getId()));
        PageRequest pageRequest = new PageRequest(params);
        List<Member> list_Member = memberDao.listAll(pageRequest);

        List<Member> deletes = Lists.newArrayList(); // 删除列表
        List<Member> inserts = Lists.newArrayList(); // 添加列表
        List<Member> updates = Lists.newArrayList(); // 更新列表
        for (Member memberSaved : list_Member) {
            boolean found = false;
            for (Member member : project.getMemberList()) {
                if (memberSaved.getId().equals(member.getId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                deletes.add(memberSaved);
            }
        }
        if (deletes.size() > 0) {
            memberService.bulkDelete(deletes);
        }
        for (Member member : project.getMemberList()) {
            if (StringUtils.isBlank(member.getId())) {
                member.setProject(project);

                inserts.add(member);
            } else {
                updates.add(member);
            }
        }
        if (updates.size() > 0) {
            memberService.bulkUpdate(updates);
        }
        if (inserts.size() > 0) {
            memberService.bulkInsert(inserts);
        }
    }

    /**
     * 保存基础方案
     * @param project
     */
    private void saveScheme(Project project) {
        String emptyProject = "30000000";
        // 保存后端方案
        dcSchemeDao.bulkInsertSchemeByProject(Long.parseLong(project.getId()), emptyProject);
        // 保存业务表
        dcTableDao.bulkInsertTableByProject(Long.parseLong(project.getId()), emptyProject);
        // 保存业务表字段
        dcTableColumnDao.bulkInsertColumnByProject(Long.parseLong(project.getId()), emptyProject);
        // 保存前端方案
        dcClienSchemeDao.bulkInsertClienByProject(Long.parseLong(project.getId()), emptyProject);
    }

    /**
     * 删除基础方案
     * @param project
     */
    private void deleteScheme(Project project) {
        // 删除后端方案
        dcSchemeDao.bulkDeleteSchemeByProject(project);
        // 删除业务表
        dcTableDao.bulkDeleteTableByProject(project);
        // 删除业务表字段
        dcTableColumnDao.bulkDeleteColumnByProject(project);
        // 删除前端方案
        dcClienSchemeDao.bulkDeleteClienByProject(project);
    }

    /**
     * 查询项目包含后端方案包含业务表包含业务表字段
     * @param parameters
     * @param orderby
     * @return
     */
    public List<Project> listProjectTableColumn(List<Parameter> parameters, String orderby) {
        PageRequest pageRequest = new PageRequest(parameters, orderby);
        List<Project> list = dao.listProjectTableColumn(pageRequest);
        return list;
    }

    /**
     * 数据源查询所有项目
     * @return
     */
    public List<Project> listForDataSource() {
        return dao.listForDataSource();
    }
}
