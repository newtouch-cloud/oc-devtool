package com.geeke.project.annotation;

import java.lang.annotation.*;

/**
 * @author FallenRunning(TBH)
 * 解耦项目表的业务 添加数据源和项目实体的增删改查应该分离开
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceChange {
    boolean remove() default false;  //是否是移除数据源的操作 默认不是
}
