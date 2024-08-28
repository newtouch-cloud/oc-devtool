package com.geeke.dc.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.dc.entity.DcMobileScheme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 移动端方案DAO接口
 * @author
 * @version
 */
@Mapper
public interface DcMobileSchemeDao extends CrudDao<DcMobileScheme> {}
