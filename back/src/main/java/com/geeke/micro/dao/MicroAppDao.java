package com.geeke.micro.dao;

import com.geeke.common.persistence.CrudDao;
import com.geeke.micro.entity.MicroApp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 应用管理DAO接口
 * @author
 * @version
 */
@Mapper
public interface MicroAppDao extends CrudDao<MicroApp> {
    MicroApp getAppByKey(@Param("appKey") String appKey);
}
