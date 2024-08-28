package com.geeke.dc.service;

import com.geeke.common.constants.DbType;
import com.geeke.common.data.Parameter;
import com.geeke.common.service.CrudService;
import com.geeke.dc.dao.SchemaTableDao;
import com.geeke.dc.entity.DcTable;
import com.geeke.utils.JdbcTypeUtils;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

/**
 * 系统表控件Service
 * @author lys
 * @version 2019-08-31
 */
 
@Service("schemaTableService")
@Transactional(readOnly = true)
public class SchemaTableService extends CrudService<SchemaTableDao, DcTable>{

    @Autowired
    private DataSource dataSource;

    @SneakyThrows
    @Override
    public List<DcTable> listAll(List<Parameter> parameters, String orderby) {
        DbType type = JdbcTypeUtils.getType(dataSource);
        if(type == DbType.MYSQL){
            return super.listAll(parameters, orderby);
        }
        if(type == DbType.SQLSERVER){
            return this.dao.listAllSqlServer(parameters, orderby);
        }
        if(type == DbType.ORACLE){
            return this.dao.listAllOracle(parameters, orderby);
        }
        return null;
    }
}