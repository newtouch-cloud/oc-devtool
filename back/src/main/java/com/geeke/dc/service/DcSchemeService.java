package com.geeke.dc.service;

import com.geeke.common.constants.ActionConstants;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.dc.dao.DcClienSchemeDao;
import com.geeke.dc.dao.DcSchemeDao;
import com.geeke.dc.dao.DcTableColumnDao;
import com.geeke.dc.dao.DcTableDao;
import com.geeke.dc.entity.DcClienScheme;
import com.geeke.dc.entity.DcScheme;
import com.geeke.dc.entity.DcTable;
import com.geeke.dc.service.DcTableService;
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
 * 后端方案Service
 * @author
 * @version
 */

@Service("dcSchemeService")
@Transactional(readOnly = true)
public class DcSchemeService extends CrudService<DcSchemeDao, DcScheme> {

    @Autowired
    private DcTableDao dcTableDao;

    @Autowired
    private DcTableService dcTableService;

    @Autowired
    private DcTableColumnDao dcTableColumnDao;

    @Autowired
    private DcClienSchemeDao clienSchemeDao;

    @Override
    public DcScheme get(String id) {
        DcScheme dcScheme = super.get(id);

        List<Parameter> params = null;
        PageRequest pageRequest;
        /*获取子表列表   低代码-业务表*/
        params = Lists.newArrayList();
        params.add(new Parameter("scheme_id", "=", dcScheme.getId()));
        pageRequest = new PageRequest(params);
        List<DcTable> dcTables = dcTableDao.listAll(pageRequest);
        /*获取低代码-业务表子表列表   低代码-业务表字段*/
        if (dcTables.size() > 0) {
            for (int i = 0; i < dcTables.size(); i++) {
                List<Parameter> schemeParams = null;
                PageRequest schemePageRequest;
                /*获取和当前表在同一方案下的其他表*/
                schemeParams = Lists.newArrayList();
                schemeParams.add(new Parameter("scheme_id", "=", dcScheme.getId()));
                schemeParams.add(new Parameter("id", "!=", dcTables.get(i).getId()));
                schemePageRequest = new PageRequest(schemeParams);
                dcTables.get(i).setAssociationList(dcTableDao.listAll(schemePageRequest));

                List<Parameter> columnParams = null;
                PageRequest columnPageRequest;
                /*获取子表列表   低代码-业务表*/
                columnParams = Lists.newArrayList();
                columnParams.add(new Parameter("dc_table_id", "=", dcTables.get(i).getId()));
                columnPageRequest = new PageRequest(columnParams);
                dcTables.get(i).setColumnList(dcTableColumnDao.listAllGroupById(columnPageRequest));
            }
        }
        dcScheme.setDcTableList(dcTables);
        return dcScheme;
    }

    @Transactional(readOnly = false)
    @Override
    public DcScheme save(DcScheme dcScheme) {
        DcScheme dcSchemeTemp = super.save(dcScheme);

        /* 保存子表数据     低代码-业务表 */
        saveDcTableList(dcSchemeTemp);

        return dcSchemeTemp;
    }

    /**
     * 删除
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public int delete(DcScheme dcScheme) {
        // 判断该方案是否被使用中
        List<Parameter> schemeParams = null;
        PageRequest schemePageRequest;
        /* 处理子表     低代码-业务表 */
        schemeParams = Lists.newArrayList();
        schemeParams.add(new Parameter("scheme_id", "=", dcScheme.getId()));
        schemePageRequest = new PageRequest(schemeParams);
        List<DcClienScheme> dcClienSchemes = clienSchemeDao.listAll(schemePageRequest);
        if (dcClienSchemes != null && dcClienSchemes.size() > 0) {
            throw new CommonJsonException(ResultUtil.warningJson(ErrorEnum.E_50001, "此方案使用中，不可删除"));
        }

        List<Parameter> params = null;
        PageRequest pageRequest;
        /* 处理子表     低代码-业务表 */
        params = Lists.newArrayList();
        params.add(new Parameter("scheme_id", "=", dcScheme.getId()));
        pageRequest = new PageRequest(params);
        dcScheme.setDcTableList(dcTableDao.listAll(pageRequest));

        if (dcScheme.getDcTableList() != null && dcScheme.getDcTableList().size() > 0) {
            for (int i = 0; i < dcScheme.getDcTableList().size(); i++) {
                dcTableService.delete(dcScheme.getDcTableList().get(i));
            }
        }
        int rows = super.delete(dcScheme);
        return rows;
    }

    /**
     * 批量删除
     * @param entitys
     */
    @Override
    @Transactional(readOnly = false)
    public int bulkDelete(List<DcScheme> dcSchemeList) {
        for (DcScheme dcScheme : dcSchemeList) {
            delete(dcScheme);
        }

        return dcSchemeList.size();
    }

    /**
     * 生成操作日志
     * @param actionTypeId  操作类型Id
     * @param entity        操作的实体对象
     * @return
     */
    @Override
    protected Action createAction(String actionTypeId, DcScheme entity) {
        Action action = super.createAction(actionTypeId, entity);
        if (action == null) {
            return null;
        }
        // 删除时记录把保存的数据保存到回收站
        if (ActionConstants.ACTION_DELETED.equals(actionTypeId)) {
            for (DcTable child : entity.getDcTableList()) {
                ActionRecycle recycle = new ActionRecycle();
                recycle.setTableName(child.getBusTableName());
                recycle.setObjectId(child.getId());
                recycle.setObjectName((String) Reflections.invokeGetter(child, "name"));
                action.getActionRecycleList().add(recycle);
            }
        }
        return action;
    }

    /* 保存子表数据     低代码-业务表 */
    private void saveDcTableList(DcScheme dcScheme) {
        List<Parameter> params = Lists.newArrayList();

        params.add(new Parameter("scheme_id", "=", dcScheme.getId()));
        PageRequest pageRequest = new PageRequest(params);
        List<DcTable> list_DcTable = dcTableDao.listAll(pageRequest);

        List<DcTable> deletes = Lists.newArrayList(); // 删除列表
        List<DcTable> inserts = Lists.newArrayList(); // 添加列表
        List<DcTable> updates = Lists.newArrayList(); // 更新列表
        for (DcTable dcTableSaved : list_DcTable) {
            boolean found = false;
            for (DcTable dcTable : dcScheme.getDcTableList()) {
                if (dcTableSaved.getId().equals(dcTable.getId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                deletes.add(dcTableSaved);
            }
        }
        if (deletes.size() > 0) {
            dcTableService.bulkDelete(deletes);
        }
        for (DcTable dcTable : dcScheme.getDcTableList()) {
            if (StringUtils.isBlank(dcTable.getId())) {
                dcTable.getScheme().setId(dcScheme.getId());

                inserts.add(dcTable);
            } else {
                updates.add(dcTable);
            }
        }
        if (updates.size() > 0) {
            dcTableService.bulkUpdate(updates);
        }
        if (inserts.size() > 0) {
            dcTableService.bulkInsert(inserts);
        }
        // 保存表字段信息
        if (dcScheme.getDcTableList().size() > 0) {
            for (int i = 0; i < dcScheme.getDcTableList().size(); i++) {
                dcTableService.save(dcScheme.getDcTableList().get(i));
            }
        }
    }
}
