package com.geeke.dc.dao;

import com.geeke.common.data.PageRequest;
import com.geeke.common.persistence.CrudDao;
import com.geeke.dc.entity.DcScheme;
import com.geeke.project.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后端方案DAO接口
 * @author
 * @version
 */
@Mapper
public interface DcSchemeDao extends CrudDao<DcScheme> {
    /**
     * 查询后端方案包含业务表包含业务表字段
     * @param var1
     * @return
     */
    List<DcScheme> listSchemeTableColumn(PageRequest var1);

    int bulkInsertSchemeByProject(@Param("projectId") Long projectId, @Param("emptyProject")String emptyProject);

    int bulkDeleteSchemeByProject(Project project);
}
