package com.geeke.dc.dao;

import com.geeke.common.data.PageRequest;
import com.geeke.common.persistence.CrudDao;
import com.geeke.dc.entity.DcTable;
import com.geeke.dc.entity.DcTableColumn;
import java.util.List;

import com.geeke.project.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 后端方案DAO接口
 * @author
 * @version
 */
@Mapper
public interface DcTableDao extends CrudDao<DcTable> {
    /**
     * 查询表字段
     * @param var1
     * @return
     */
    List<DcTableColumn> listTableColumn(PageRequest var1);

    List<DcTableColumn> listTableColumnSqlServer(PageRequest var1);

    List<DcTableColumn> listTableColumnOracle(PageRequest var1);

    int bulkInsertTableByProject(@Param("projectId") Long projectId, @Param("emptyProject")String emptyProject);

    int bulkDeleteTableByProject(Project project);
}
