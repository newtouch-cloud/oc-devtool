package com.geeke.project.aspect;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.hikari.HikariCpConfig;
import com.geeke.dbtype.entity.SysDbType;
import com.geeke.project.annotation.DataSourceChange;
import com.geeke.project.entity.Project;
import com.geeke.utils.JdbcUtils;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Objects;

/**
 * @author FallenRunning(TBH)
 * 执行具体的数据源操作逻辑
 */
@Slf4j
@Aspect
@Component
public class DataSourceChangeAspect {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DefaultDataSourceCreator dataSourceCreator;

    @Pointcut("@annotation(com.geeke.project.annotation.DataSourceChange)")
    public void point() {
    }

    @Around("point() && @annotation(dataSourceChange)")
    public Object execute(ProceedingJoinPoint pjp, DataSourceChange dataSourceChange) throws Throwable {
        //准备参数
        Object[] args = pjp.getArgs();
        Object proceed = pjp.proceed();
        Project project = null;
        //开始后置通知 如果期间报错了不管 下边的逻辑是建立在执行成功上的 首先拿到project实体
        if (Objects.nonNull(args)) {
            for (Object arg : args) {
                if (arg instanceof Project) {
                    project = (Project) arg;
                    break;
                }
            }
        }
        Objects.requireNonNull(project, "参数列表无法Project实体信息");
        log.info("projectId信息:{}", project.getId());
        boolean remove = dataSourceChange.remove();
        if (remove) {
            //删除数据源
            removeDataSource(project.getId());
            log.info("移除数据源成功!");
        }
        if (!remove) {
            //后边不管是更新还是新增数据源 直接重新加载一次 数据源存在于Map 会自动覆盖
            //先校验project是否完整 不完整的不用加
            String id = project.getId();
            String username = project.getDbUsername();
            String password = project.getDbPassword();
            String url = project.getDbUrl();
            SysDbType dbType = project.getDbType();
            String driver = Objects.isNull(dbType) ? "" : dbType.getDriver();
            if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(url)
                    && StringUtils.isNotEmpty(driver) && StringUtils.isNotEmpty(id)
            ) {
                //在动态预加载数据源的时候先获取连接 以确定是否需要加载
                JdbcUtils jdbcUtils = new JdbcUtils(username, password,url,driver);
                try {
                    Connection connection = jdbcUtils.getConnection();
                    if (Objects.nonNull(connection)) {
                        DataSourceProperty dataSourceProperty = new DataSourceProperty();
                        dataSourceProperty.setDriverClassName(driver);
                        dataSourceProperty.setUrl(url);
                        dataSourceProperty.setUsername(username);
                        dataSourceProperty.setPassword(password);
                        dataSourceProperty.setType(HikariDataSource.class);
                        //开启数据源懒加载
                        HikariCpConfig config = new HikariCpConfig();
                        config.setConnectionTimeout(6000L);
                        config.setMaximumPoolSize(500);
                        dataSourceProperty.setHikari(config);
                        dataSourceProperty.setLazy(true);
                        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
                        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
                        ds.addDataSource(id, dataSource);
                        log.info("预加载数据源成功!");
                    }
                    //也要关闭打开的连接
                    jdbcUtils.close(connection);
                } catch (Exception e) {
                    //如果出错了 说明数据填写了 但是有问题 连不上数据库 把原本的数据源也移除掉 现在信息是错误的了
                    removeDataSource(project.getId());
                    log.info("project的数据完整但是存在问题,连接不到数据库!");
                }
            } else {
                //如果走了这步操作 就是把原本正确的数据源改错误了 我们就移除这个数据源
                removeDataSource(project.getId());
                log.info("修改了原本的信息缺少数据源参数,移除原本的数据源：{}!", id);
            }
        }
        return proceed;
    }

    private void removeDataSource(String id) {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        ds.removeDataSource(id);
    }
}
