package com.geeke.dbtype.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.dbtype.entity.SysDbType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 数据库类型管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface SysDbTypeDao extends CrudDao<SysDbType> {}
