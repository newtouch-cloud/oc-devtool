package com.geeke.dc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.dc.entity.DcScheme;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 后端方案Entity
 * @author
 * @version
 */

public class DcTable extends DataEntity<DcTable> {

    private static final long serialVersionUID = 1339746483134570553L;

    private String name; // 表

    private String source; // 表来源

    private DcScheme scheme; // 代码方案

    private String className; // 实体类名

    private String comments; // 说明

    private String parentTableFk; // 父表外键

    private String isMainTable; // 是否主表（0：否，1：是）

    private String isAssociation; // 是否关联表（0：否，1：是）

    private String associationField; // 关联对象字段

    private String checkVersion; // 是否检查版本

    private String filter; // 筛选条件

    private String constraintJson; // 表约束

    private String orderColumns; // 排序字段

    private String isOneToOne; // 是否一对一表（0：否，1：是）

    private boolean isTreeTable; // 获取自定义类型custom关联表是否是树形
    private Boolean delFlagExists; // 获取自定义类型custom|this关联表是否包含删除字段
    private List<DcTable> associationList = Lists.newArrayList(); // 当前表的同方案下其他表

    private List<DcTableColumn> columnList = Lists.newArrayList(); // 子表列表

    // 构造方法
    public DcTable() {
        super();
    }

    public DcTable(String id) {
        super(id);
    }

    public DcTable(DcScheme scheme) {
        this.scheme = scheme;
    }

    // 生成get和set方法

    @Length(min = 1, max = 128, message = "表长度必须介于 1 和 128 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 128, message = "表来源长度必须介于 1 和 128 之间")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @NotNull(message = "代码方案不能为空")
    public DcScheme getScheme() {
        return scheme;
    }

    public void setScheme(DcScheme scheme) {
        this.scheme = scheme;
    }

    @Length(min = 1, max = 128, message = "实体类名长度必须介于 1 和 128 之间")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Length(min = 0, max = 64, message = "说明长度必须介于 0 和 64 之间")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Length(min = 0, max = 64, message = "父表外键长度必须介于 0 和 64 之间")
    public String getParentTableFk() {
        return parentTableFk;
    }

    public void setParentTableFk(String parentTableFk) {
        this.parentTableFk = parentTableFk;
    }

    @NotNull(message = "是否主表（0：否，1：是）不能为空")
    public String getIsMainTable() {
        return isMainTable;
    }

    public void setIsMainTable(String isMainTable) {
        this.isMainTable = isMainTable;
    }

    public String getIsAssociation() {
        return isAssociation;
    }

    public void setIsAssociation(String isAssociation) {
        this.isAssociation = isAssociation;
    }

    @Length(min = 0, max = 64, message = "关联对象字段长度必须介于 0 和 64 之间")
    public String getAssociationField() {
        return associationField;
    }

    public void setAssociationField(String associationField) {
        this.associationField = associationField;
    }

    @NotNull(message = "是否检查版本不能为空")
    public String getCheckVersion() {
        return checkVersion;
    }

    public void setCheckVersion(String checkVersion) {
        this.checkVersion = checkVersion;
    }

    @Length(min = 0, max = 2048, message = "筛选条件长度必须介于 0 和 2048 之间")
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Length(min = 0, max = -1, message = "表约束长度必须介于 0 和 -1 之间")
    public String getConstraintJson() {
        return constraintJson;
    }

    public void setConstraintJson(String constraintJson) {
        this.constraintJson = constraintJson;
    }

    @Length(min = 0, max = 2048, message = "排序字段长度必须介于 0 和 2048 之间")
    public String getOrderColumns() {
        return orderColumns;
    }

    public void setOrderColumns(String orderColumns) {
        this.orderColumns = orderColumns;
    }

    public String getIsOneToOne() {
        return isOneToOne;
    }

    public void setIsOneToOne(String isOneToOne) {
        this.isOneToOne = isOneToOne;
    }

    public boolean getIsTreeTable() {
        return isTreeTable;
    }

    public void setIsTreeTable(boolean isTreeTable) {
        this.isTreeTable = isTreeTable;
    }

    public Boolean getDelFlagExists() {
        return delFlagExists;
    }

    public void setDelFlagExists(Boolean delFlagExists) {
        this.delFlagExists = delFlagExists;
    }

    public List<DcTableColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<DcTableColumn> columnList) {
        this.columnList = columnList;
    }

    @JsonIgnore
    public List<DcTable> getAssociationList() {
        return this.associationList;
    }

    public void setAssociationList(List<DcTable> associationList) {
        this.associationList = associationList;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "dc_table";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1339746483134570553";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
