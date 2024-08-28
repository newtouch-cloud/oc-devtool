package com.geeke.project.dao;

import com.geeke.common.data.PageRequest;
import com.geeke.common.persistence.CrudDao;
import com.geeke.project.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface ProjectDao extends CrudDao<Project> {
    /**
     * 查询项目包含后端方案包含业务表包含业务表字段
     * @param var1
     * @return
     */
	public List<Project> listProjectTableColumn(PageRequest var1);
    
    
    /**
     * 数据源查询所有项目
     * @return
     */
    public List<Project> listForDataSource();
}
