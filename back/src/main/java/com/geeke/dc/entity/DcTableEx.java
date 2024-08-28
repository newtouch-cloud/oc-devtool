package com.geeke.dc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.utils.StringUtils;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class DcTableEx extends DcTable {

    private static final long serialVersionUID = 57593538386163538L;

    public DcTableEx() {}

    public List<String> getImportList() {
        List<String> importList = Lists.newArrayList();
        Iterator var2 = this.getColumnList().iterator();

        while (var2.hasNext()) {
            DcTableColumn c = (DcTableColumn) var2.next();
            DcTableColumnEx column = (DcTableColumnEx) c;
            String ann;
            if (column.getJavaType() != null && "Custom".equals(column.getJavaType().getValue())) {
                DcTable table = column.getCustomType();
                if (table != null && table.getScheme() != null) {
                    ann = table.getScheme().getPackageName() + "." + table.getScheme().getModuleName();
                    if (!StringUtils.isNullOrEmpty(table.getScheme().getSubModuleName())) {
                        ann = ann + "." + table.getScheme().getSubModuleName() + ".entity";
                    } else {
                        ann = ann + ".entity";
                    }

                    ann = ann + "." + table.getClassName();
                    if (!importList.contains(ann)) {
                        importList.add(ann);
                    }
                }
            } else if (column.getJavaType() != null && "This".equals(column.getJavaType().getValue())) { // TODO
                for (int i = 0; i < this.getAssociationList().size(); i++) {
                    if (column.getCurrentSchemeTable().equals(this.getAssociationList().get(i).getName())) {
                        ann = this.getScheme().getPackageName() + "." + this.getScheme().getModuleName();
                        if (!StringUtils.isNullOrEmpty(this.getScheme().getSubModuleName())) {
                            ann = ann + "." + this.getScheme().getSubModuleName() + ".entity";
                        } else {
                            ann = ann + ".entity";
                        }
                        ann = ann + "." + this.getAssociationList().get(i).getClassName();
                        if (!importList.contains(ann)) {
                            importList.add(ann);
                        }
                    }
                }
            }

            if (
                (
                    column.getIsNotBaseField() &&
                    StringUtils.indexOf(column.getJavaType().getValue(), ".") != -1 &&
                    !importList.contains(column.getJavaType().getValue())
                )
            ) {
                importList.add(column.getJavaType().getValue());
            }

            if (column.getIsNotBaseField()) {
                Iterator var7 = column.getAnnotationList().iterator();

                while (var7.hasNext()) {
                    ann = (String) var7.next();
                    if (!importList.contains(StringUtils.substringBeforeLast(ann, "("))) {
                        importList.add(StringUtils.substringBefore(ann, "("));
                    }
                }
            }

            if ("Long".equals(column.getJavaType().getValue())) {
                if (!importList.contains("com.fasterxml.jackson.databind.annotation.JsonSerialize")) {
                    importList.add("com.fasterxml.jackson.databind.annotation.JsonSerialize");
                }

                if (!importList.contains("com.fasterxml.jackson.databind.ser.std.ToStringSerializer")) {
                    importList.add("com.fasterxml.jackson.databind.ser.std.ToStringSerializer");
                }
            }
        }

        // 当前表是主表
        if (("1").equals(this.getIsMainTable())) {
            if (!importList.contains("java.util.List")) {
                importList.add("java.util.List");
            }

            if (!importList.contains("com.google.common.collect.Lists")) {
                importList.add("com.google.common.collect.Lists");
            }
        }

        return importList;
    }

    public Boolean getParentExists() {
        return (
            ("1").equals(this.getIsMainTable()) &&
            StringUtils.isNotBlank(this.getId()) &&
            StringUtils.isNotBlank(this.getParentTableFk())
        );
    }

    public Boolean getIdExists() {
        Iterator var1 = this.getColumnList().iterator();

        DcTableColumn c;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            c = (DcTableColumn) var1.next();
        } while (!StringUtils.equalsIgnoreCase("id", c.getName()));

        return true;
    }

    public Boolean getCreateDateExists() {
        Iterator var1 = this.getColumnList().iterator();

        DcTableColumn c;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            c = (DcTableColumn) var1.next();
        } while (!StringUtils.equalsIgnoreCase("create_date", c.getName()));

        return true;
    }

    public Boolean getUpdateDateExists() {
        Iterator var1 = this.getColumnList().iterator();

        DcTableColumn c;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            c = (DcTableColumn) var1.next();
        } while (!StringUtils.equalsIgnoreCase("update_date", c.getName()));
        this.setCheckVersion("1");
        return true;
    }

    public Boolean getUpdateByExists() {
        Iterator var1 = this.getColumnList().iterator();

        DcTableColumn c;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            c = (DcTableColumn) var1.next();
        } while (!StringUtils.equalsIgnoreCase("update_by", c.getName()));
        return true;
    }

    public Boolean getSortExists() {
        Iterator var1 = this.getColumnList().iterator();

        DcTableColumn c;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            c = (DcTableColumn) var1.next();
        } while (!StringUtils.equalsIgnoreCase("sort", c.getName()));

        return true;
    }

    public DcTableColumn getSortColumn() {
        Iterator var1 = this.getColumnList().iterator();

        DcTableColumn c;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            c = (DcTableColumn) var1.next();
        } while (!StringUtils.equalsIgnoreCase("sort", c.getName()));

        return c;
    }

    public Boolean getDelFlagExists() {
        Iterator var1 = this.getColumnList().iterator();

        DcTableColumn c;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            c = (DcTableColumn) var1.next();
        } while (!StringUtils.equalsIgnoreCase("del_flag", c.getName()));

        return true;
    }

    /**
     * 是否包含远程调用字段
     * @return
     */
    public Boolean getRemoteFieldExists() {
        for (DcTableColumn column : getColumnList()) {
            if (StringUtils.equalsIgnoreCase(column.getJavaType().getValue(), "Remote")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否为树形表
     * @return
     */
    public boolean getIsTreeTable() {
        for (DcTableColumn column : getColumnList()) {
            //java类型为This, 并且字段名为parent_id
            if (
                column.getJavaType() != null &&
                "This".equals(column.getJavaType().getValue()) &&
                StringUtils.equalsIgnoreCase("parent_id", column.getName())
            ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否包含自动编号类型的字段
     * @return
     */
    public boolean getAutoSerialExists() {
        for (DcTableColumn column : getColumnList()) {
            if (StringUtils.equalsIgnoreCase(column.getJavaType().getValue(), "Code")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否包密码字段
     * @return
     */
    public boolean getHasPassWordInput() {
        for (DcTableColumn column : getColumnList()) {
            if (StringUtils.equalsIgnoreCase(column.getJavaType().getValue(), "Password")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否多文件上传组件
     * @return
     */
    public boolean getMutilFileUploadExists() {
        for (DcTableColumn column : getColumnList()) {
            if (
                column.getJavaType() != null && "File".equals(column.getJavaType().getValue()) && "1".equals(column.getIsEdit())
            ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否多图片上传
     * @return
     */
    public boolean getImageUploadExists() {
        for (DcTableColumn column : getColumnList()) {
            if (
                column.getJavaType() != null &&
                "Image".equals(column.getJavaType().getValue()) &&
                "1".equals(column.getIsEdit())
            ) {
                return true;
            }
        }
        return false;
    }

    @JsonIgnore
    public DcTableColumn getParentColumn() {
        Iterator var1 = this.getColumnList().iterator();

        DcTableColumn column;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            column = (DcTableColumn) var1.next();
        } while (
            column.getJavaType() == null ||
            !"This".equals(column.getJavaType().getValue()) ||
            !StringUtils.equalsIgnoreCase("parent_id", column.getName())
        );

        return column;
    }
}
