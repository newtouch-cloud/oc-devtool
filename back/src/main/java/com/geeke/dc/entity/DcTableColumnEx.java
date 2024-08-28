package com.geeke.dc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.utils.StringUtils;
import com.google.common.collect.Lists;
import java.util.List;

public class DcTableColumnEx extends DcTableColumn {

    private static final long serialVersionUID = 1L;

    public DcTableColumnEx() {}

    public Integer getDataLength() {
        String[] ss = StringUtils.split(StringUtils.substringBetween(this.getJdbcType(), "(", ")"), ",");
        return ss != null && ss.length >= 1 ? Integer.parseInt(ss[0]) : -1;
    }

    public Integer getDecimalLenth() {
        if ("java.math.BigDecimal".equals(this.getJavaType().getValue())) {
            return 2;
        } else if (!"Long".equals(this.getJavaType().getValue()) && !"Integer".equals(this.getJavaType().getValue())) {
            if (!"Double".equals(this.getJavaType().getValue()) && !"Float".equals(this.getJavaType().getValue())) {
                return 0;
            } else {
                String[] ss = StringUtils.split(StringUtils.substringBetween(this.getJdbcType(), "(", ")"), ",");
                return ss != null && ss.length >= 2 ? Integer.parseInt(ss[1]) : 2;
            }
        } else {
            return 0;
        }
    }

    public String getSimpleJavaType() {
        if (this.getJavaType() != null && "This".equals(this.getJavaType().getValue())) {
            return StringUtils.capitalize(null != this.getDcTable() ? this.getDcTable().getClassName() : "");
        } else if (
            this.getJavaType() != null &&
            (
                "Password".equals(this.getJavaType().getValue()) ||
                "File".equals(this.getJavaType().getValue()) ||
                "Image".equals(this.getJavaType().getValue()) ||
                "Code".equals(this.getJavaType().getValue()) ||
                "Month".equals(this.getJavaType().getValue()) ||
                "Mutilple".equals(this.getJavaType().getValue()) ||
                "Time".equals(this.getJavaType().getValue()) ||
                "Date".equals(this.getJavaType().getValue())
            ) ||
            "Boolean".equals(this.getJavaType().getValue())
        ) {
            return "String";
        } else {
            return StringUtils.indexOf(this.getJavaType().getValue(), ".") != -1
                ? StringUtils.substringAfterLast(this.getJavaType().getValue(), ".")
                : this.getJavaType().getValue();
        }
    }

    @JsonIgnore
    public List<String> getAnnotationList() {
        List<String> list = Lists.newArrayList();
        if (!"1".equals(this.getIsNull()) && !"String".equals(this.getJavaType().getValue())) {
            list.add("javax.validation.constraints.NotNull(message=\"" + this.getComments() + "不能为空\")");
        } else if (
            !"1".equals(this.getIsNull()) && "String".equals(this.getJavaType().getValue()) && !"0".equals(this.getDataLength())
        ) {
            list.add(
                "org.hibernate.validator.constraints.Length(min=1, max=" +
                this.getDataLength() +
                ", message=\"" +
                this.getComments() +
                "长度必须介于 1 和 " +
                this.getDataLength() +
                " 之间\")"
            );
        } else if ("String".equals(this.getJavaType().getValue()) && !"0".equals(this.getDataLength())) {
            list.add(
                "org.hibernate.validator.constraints.Length(min=0, max=" +
                this.getDataLength() +
                ", message=\"" +
                this.getComments() +
                "长度必须介于 0 和 " +
                this.getDataLength() +
                " 之间\")"
            );
        }

        return list;
    }

    /**
     * 获取简写列注解列表
     * @return
     */
    public List<String> getSimpleAnnotationList() {
        List<String> list = Lists.newArrayList();
        for (String ann : getAnnotationList()) {
            list.add(StringUtils.substringAfterLast(ann, "."));
        }
        return list;
    }

    public Boolean getIsNotBaseField() {
        DcTable dcTable = getDcTable();
        return (
            !StringUtils.equals(getName(), "id") &&
            !StringUtils.equals(getName(), "remarks") &&
            !StringUtils.equals(getName(), "create_by") &&
            !StringUtils.equals(getName(), "create_date") &&
            !StringUtils.equals(getName(), "update_by") &&
            !StringUtils.equals(getName(), "update_date") &&
            !StringUtils.equals(getName(), "del_flag") &&
            !StringUtils.equals(getName(), "parent_id") &&
            !StringUtils.equals(getName(), "ids") &&
            !StringUtils.equals(getName(), "proc_status") &&
            !StringUtils.equals(getName(), "proc_inst") &&
            !(null != dcTable && dcTable.getIsTreeTable() && StringUtils.equals(getName(), "sort"))
        );
    }

    public Boolean getIsEditableField() {
        return (
            !StringUtils.equals(this.getName(), "id") &&
            !StringUtils.equals(this.getName(), "create_by") &&
            !StringUtils.equals(this.getName(), "create_date") &&
            !StringUtils.equals(this.getName(), "update_by") &&
            !StringUtils.equals(this.getName(), "update_date") &&
            !StringUtils.equals(this.getName(), "del_flag") &&
            !StringUtils.equals(this.getName(), "ids") &&
            !StringUtils.equals(this.getName(), "proc_status") &&
            !StringUtils.equals(this.getName(), "proc_inst")
        );
    }
}
