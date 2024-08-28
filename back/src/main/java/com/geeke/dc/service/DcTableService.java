package com.geeke.dc.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.geeke.common.constants.ActionConstants;
import com.geeke.common.constants.DbType;
import com.geeke.common.data.PageRequest;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.config.exception.CommonJsonException;
import com.geeke.dc.dao.DcTableColumnDao;
import com.geeke.dc.dao.DcTableDao;
import com.geeke.dc.entity.DcTable;
import com.geeke.dc.entity.DcTableColumn;
import com.geeke.dc.service.DcTableColumnService;
import com.geeke.sys.entity.Action;
import com.geeke.sys.entity.ActionRecycle;
import com.geeke.utils.JdbcTypeUtils;
import com.geeke.utils.Reflections;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import com.geeke.utils.constants.ErrorEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 后端方案Service
 * @author
 * @version
 */

@Service("dcTableService")
@Transactional(readOnly = true)
public class DcTableService extends CrudService<DcTableDao, DcTable> {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DcTableColumnDao dcTableColumnDao;

	@Autowired
	DcTableColumnService  dcTableColumnService;

    @Override
    public DcTable get(String id) {
        DcTable dcTable = super.get(id);

        List<Parameter> params = null;
        PageRequest pageRequest;
        /*获取子表列表   低代码-业务表字段*/
        params = Lists.newArrayList();
        params.add(new Parameter("dc_table_id", "=", dcTable.getId()));
        pageRequest = new PageRequest(params);
        dcTable.setColumnList(dcTableColumnDao.listAll(pageRequest));
        return dcTable;
    }

    @Override
    @Transactional(readOnly = false)
    public DcTable save(DcTable dcTable) {
        DcTable dcTableTemp = super.save(dcTable);
        if (StringUtils.isNoneBlank(dcTableTemp.getId())) {
            /* 保存子表数据     低代码-业务表字段 */
            saveDcTableColumnList(dcTableTemp);
        }
        return dcTableTemp;
    }

    /**
     * 删除
     * @param entity
     */
    @Override
    @Transactional(readOnly = false)
    public int delete(DcTable dcTable) {
        List<Parameter> params = null;
        PageRequest pageRequest;
        /* 处理子表     低代码-业务表字段 */
        params = Lists.newArrayList();
        params.add(new Parameter("dc_table_id", "=", dcTable.getId()));
        pageRequest = new PageRequest(params);
        dcTable.setColumnList(dcTableColumnDao.listAll(pageRequest));

        if (dcTable.getColumnList() != null && dcTable.getColumnList().size() > 0) {
            dcTableColumnService.bulkDelete(dcTable.getColumnList());
        }

        int rows = super.delete(dcTable);
        return rows;
    }

    /**
     * 批量删除
     * @param entitys
     */
    @Override
    @Transactional(readOnly = false)
    public int bulkDelete(List<DcTable> dcTableList) {
        for (DcTable dcTable : dcTableList) {
            delete(dcTable);
        }
        return dcTableList.size();
    }

    /**
     * 生成操作日志
     * @param actionTypeId  操作类型Id
     * @param entity        操作的实体对象
     * @return
     */
    @Override
    protected Action createAction(String actionTypeId, DcTable entity) {
        Action action = super.createAction(actionTypeId, entity);
        if (action == null) {
            return null;
        }
        // 删除时记录把保存的数据保存到回收站
        if (ActionConstants.ACTION_DELETED.equals(actionTypeId)) {
            for (DcTableColumn child : entity.getColumnList()) {
                ActionRecycle recycle = new ActionRecycle();
                recycle.setTableName(child.getBusTableName());
                recycle.setObjectId(child.getId());
                recycle.setObjectName((String) Reflections.invokeGetter(child, "name"));
                action.getActionRecycleList().add(recycle);
            }
        }
        return action;
    }

    /* 保存子表数据     低代码-业务表字段 */
    private void saveDcTableColumnList(DcTable dcTable) {
        List<Parameter> params = Lists.newArrayList();
        params.add(new Parameter("dc_table_id", "=", dcTable.getId()));
        PageRequest pageRequest = new PageRequest(params);
        List<DcTableColumn> list_DcTableColumn = dcTableColumnDao.listAll(pageRequest);
        List<DcTableColumn> deletes = Lists.newArrayList(); // 删除列表
        List<DcTableColumn> inserts = Lists.newArrayList(); // 添加列表
        List<DcTableColumn> updates = Lists.newArrayList(); // 更新列表
        for (DcTableColumn dcTableColumnSaved : list_DcTableColumn) {
            boolean found = false;
            for (DcTableColumn dcTableColumn : dcTable.getColumnList()) {
                if (dcTableColumnSaved.getId().equals(dcTableColumn.getId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                dcTableColumnSaved.setOldUpdateDate(dcTableColumnSaved.getUpdateDate());
                deletes.add(dcTableColumnSaved);
            }
        }
        if (deletes.size() > 0) {
            dcTableColumnDao.bulkDelete(deletes);
        }
        for (DcTableColumn dcTableColumn : dcTable.getColumnList()) {
            if (StringUtils.isBlank(dcTableColumn.getId())) {
                dcTableColumn.setDcTable(new DcTable(dcTable.getId()));
                dcTableColumn.preInsert();
                inserts.add(dcTableColumn);
            } else {
                dcTableColumn.preUpdate();
                updates.add(dcTableColumn);
            }
        }
        if (updates.size() > 0) {
            dcTableColumnDao.bulkUpdate(updates);
        }
        if (inserts.size() > 0) {
            dcTableColumnDao.bulkInsert(inserts);
        }
    }

    @DS("#header.project_id")
    public List<DcTableColumn> listTableColumn(List<Parameter> parameters, String orderby) {
        PageRequest pageRequest = new PageRequest(parameters, orderby);
        DbType type = JdbcTypeUtils.getType(dataSource);
        if (type == DbType.MYSQL) {
            return dao.listTableColumn(pageRequest);
        }
        if (type == DbType.SQLSERVER) {
            return dao.listTableColumnSqlServer(pageRequest);
        }
        if (type == DbType.ORACLE) {
            return dao.listTableColumnOracle(pageRequest);
        }
        return null;
    }
}
