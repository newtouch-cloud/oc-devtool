package com.geeke.dc.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.dc.entity.DcClienScheme;
import com.geeke.project.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 前端方案DAO接口
 * @author
 * @version
 */
@Mapper
public interface DcClienSchemeDao extends CrudDao<DcClienScheme> {

    int bulkInsertClienByProject(@Param("projectId") Long projectId, @Param("emptyProject")String emptyProject);

    int bulkDeleteClienByProject(Project project);

}
