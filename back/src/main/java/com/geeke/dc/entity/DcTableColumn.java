package com.geeke.dc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.dc.entity.DcTable;
import com.geeke.sys.entity.DictItem;
import com.google.common.collect.Lists;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 业务表字段Entity
 * @author
 * @version
 */

public class DcTableColumn extends DataEntity<DcTableColumn> {

    private static final long serialVersionUID = 1422851351538892824L;

    private String name; // 名称

    private DcTable dcTable; // 业务表

    private String comments; // 描述

    private String jdbcType; // 列字段类型

    private DictItem javaType; // JAVA类型

    private DcTable customType; // 自定义类型

    private String currentSchemeTable; // 本方案

    private String javaField; // JAVA属性名

    private String childFields; // 子对象属性名

    private String associatedField; // 关联字段

    private String associatedFilter; // 关联条件

    private String defVal; // 默认值

    private String isPk; // 是否主键

    private String isNull; // 是否为空

    private String isInsert; // 是否可插入

    private String isEdit; // 是否可编辑

    private String settings; // 其他设置（扩展JSON字段）

    private DcTable currentTable; // 本方案对应的table信息

    // 构造方法
    public DcTableColumn() {
        super();
    }

    public DcTableColumn(String id) {
        super(id);
    }

    public DcTableColumn(DcTable dcTable) {
        this.dcTable = dcTable;
    }

    // 生成get和set方法

    @Length(min = 1, max = 128, message = "名称长度必须介于 1 和 128 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "业务表不能为空")
    public DcTable getDcTable() {
        return dcTable;
    }

    public void setDcTable(DcTable dcTable) {
        this.dcTable = dcTable;
    }

    @Length(min = 1, max = 64, message = "描述长度必须介于 1 和 64 之间")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Length(min = 1, max = 64, message = "列字段类型长度必须介于 1 和 64 之间")
    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    @NotNull(message = "JAVA类型不能为空")
    public DictItem getJavaType() {
        return javaType;
    }

    public void setJavaType(DictItem javaType) {
        this.javaType = javaType;
    }

    public DcTable getCustomType() {
        return customType;
    }

    public void setCustomType(DcTable customType) {
        this.customType = customType;
    }

    @Length(min = 0, max = 64, message = "本方案长度必须介于 0 和 64 之间")
    public String getCurrentSchemeTable() {
        return currentSchemeTable;
    }

    public void setCurrentSchemeTable(String currentSchemeTable) {
        this.currentSchemeTable = currentSchemeTable;
    }

    @Length(min = 1, max = 512, message = "JAVA属性名长度必须介于 1 和 512 之间")
    public String getJavaField() {
        return javaField;
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }

    @Length(min = 0, max = -1, message = "子对象属性名长度必须介于 0 和 -1 之间")
    public String getChildFields() {
        return childFields;
    }

    public void setChildFields(String childFields) {
        this.childFields = childFields;
    }

    @Length(min = 0, max = 64, message = "关联字段长度必须介于 0 和 64 之间")
    public String getAssociatedField() {
        return associatedField;
    }

    public void setAssociatedField(String associatedField) {
        this.associatedField = associatedField;
    }

    @Length(min = 0, max = 2000, message = "关联条件长度必须介于 0 和 2000 之间")
    public String getAssociatedFilter() {
        return associatedFilter;
    }

    public void setAssociatedFilter(String associatedFilter) {
        this.associatedFilter = associatedFilter;
    }

    @Length(min = 0, max = 2048, message = "默认值长度必须介于 0 和 2048 之间")
    public String getDefVal() {
        return defVal;
    }

    public void setDefVal(String defVal) {
        this.defVal = defVal;
    }

    @NotNull(message = "是否主键不能为空")
    public String getIsPk() {
        return isPk;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    @NotNull(message = "是否为空不能为空")
    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    @NotNull(message = "是否可插入不能为空")
    public String getIsInsert() {
        return isInsert;
    }

    public void setIsInsert(String isInsert) {
        this.isInsert = isInsert;
    }

    @NotNull(message = "是否可编辑不能为空")
    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    @Length(min = 0, max = 2048, message = "其他设置（扩展JSON字段）长度必须介于 0 和 2048 之间")
    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public DcTable getCurrentTable() {
        return currentTable;
    }

    public void setCurrentTable(DcTable currentTable) {
        this.currentTable = currentTable;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "dc_table_column";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1422851351538892824";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
