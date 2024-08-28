package com.geeke.dc.dao;

import com.geeke.common.data.PageRequest;
import com.geeke.common.persistence.CrudDao;
import com.geeke.dc.entity.DcTableColumn;
import java.util.List;

import com.geeke.project.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 业务表字段DAO接口
 * @author
 * @version
 */
@Mapper
public interface DcTableColumnDao extends CrudDao<DcTableColumn> {
    /**
     * 获取字段列表，包含获取自定义类型表是否是树形表
     * @param val
     * @return
     */
    List<DcTableColumn> listAllGroupById(PageRequest val);

    int bulkInsertColumnByProject(@Param("projectId") Long projectId, @Param("emptyProject")String emptyProject);

    int bulkDeleteColumnByProject(Project project);
}
