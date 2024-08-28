package com.geeke.databus.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.databus.entity.MasterDataType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 平台主数据DAO接口
 * @author
 * @version
 */
@Mapper
public interface MasterDataTypeDao extends CrudDao<MasterDataType> {}
