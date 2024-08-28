package com.geeke.dc.dao;

import com.geeke.common.data.Parameter;
import com.geeke.common.persistence.CrudDao;
import com.geeke.dc.entity.DcTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统表控件DAO接口
 * @author lys
 * @version 2019-08-31
 */
@Mapper
public interface SchemaTableDao extends CrudDao<DcTable> {

    List<DcTable> listAllSqlServer(List<Parameter> parameters, String orderby);

    List<DcTable> listAllOracle(List<Parameter> parameters, String orderby);
}