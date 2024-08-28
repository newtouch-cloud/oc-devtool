package com.geeke.config.datasource;

import java.util.List;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.hikari.HikariCpConfig;
import com.geeke.project.entity.Project;
import com.geeke.project.service.ProjectService;
import com.geeke.utils.JdbcUtils;
import com.zaxxer.hikari.HikariDataSource;

@Component
@Slf4j
public class DataSourceRunner implements CommandLineRunner {
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private DefaultDataSourceCreator dataSourceCreator;
    

    @Override
    public void run(String... strings) throws Exception {
        log.info(">>>>>>>>>>>>>>>执行加载项目数据源 <<<<<<<<<<<<<");
       
        List<Project> projects = projectService.listForDataSource();
        
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        HikariCpConfig config = new HikariCpConfig();
        config.setConnectionTimeout(6000L);
        config.setMaximumPoolSize(500);
        
        for(Project proj : projects) {
        	if(detect(proj)) {
	            DataSourceProperty dataSourceProperty = new DataSourceProperty();
	            dataSourceProperty.setDriverClassName(proj.getDbType().getDriver());
	            dataSourceProperty.setUrl(proj.getDbUrl());
	            dataSourceProperty.setUsername(proj.getDbUsername());
	            dataSourceProperty.setPassword(proj.getDbPassword());
	            dataSourceProperty.setPoolName(proj.getId());
	            dataSourceProperty.setType(HikariDataSource.class);
	            dataSourceProperty.setHikari(config);
	            dataSourceProperty.setLazy(true);	// 
	            
	            DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
	           
	            // PoolName就是我们yaml配置中说的数据源名称
	            ds.addDataSource(proj.getId(), dataSource);
        	}
            
        }
        log.info(">>>>>>>>>>>>>>>结束加载项目数据源 <<<<<<<<<<<<<");
    }
    
    /**
     * 检测项目数据源能否连接
     * @param proj
     * @return
     */
    private boolean detect(Project proj) {
    	boolean success = true;
        //先检测能否连接 不能连接不加载
        try {
            JdbcUtils jdbcUtils = new JdbcUtils(proj.getDbUsername(), proj.getDbPassword(), proj.getDbUrl(), proj.getDbType().getDriver());
            jdbcUtils.getConnection();
        } catch (Exception e) {
            log.error("连接不到项目[{}]数据源", proj.getName());
            
            success = false;
        }
        
        return success;
    }

}