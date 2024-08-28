package com.geeke.dc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.dc.entity.DcTable;
import com.geeke.dc.entity.DcTemplate;
import com.geeke.project.entity.Project;
import com.google.common.collect.Lists;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 后端方案Entity
 * @author
 * @version
 */

public class DcScheme extends DataEntity<DcScheme> {

    private static final long serialVersionUID = 1339746483134570552L;

    private String name; // 方案名称

    private String packageName; // 包路径

    private String moduleName; // 模块名

    private String subModuleName; // 子模块名

    private String functionName; // 功能名

    private DcTemplate template; // 模板

    private String templateJson; // 模板JSON

    private Project project; // 项目

    private boolean replace; //是否替换代码文件

    private List<DcTable> dcTableList = Lists.newArrayList(); // 子表列表

    private List<DcTable> children = Lists.newArrayList(); // 业务表集合(使用级联二级三级保证命名相同)

    // 构造方法
    public DcScheme() {
        super();
    }

    public DcScheme(String id) {
        super(id);
    }

    // 生成get和set方法

    @Length(min = 1, max = 128, message = "方案名称长度必须介于 1 和 128 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 512, message = "包路径长度必须介于 1 和 512 之间")
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Length(min = 1, max = 128, message = "模块名长度必须介于 1 和 128 之间")
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Length(min = 0, max = 128, message = "子模块名长度必须介于 0 和 128 之间")
    public String getSubModuleName() {
        return subModuleName;
    }

    public void setSubModuleName(String subModuleName) {
        this.subModuleName = subModuleName;
    }

    @Length(min = 1, max = 128, message = "功能名长度必须介于 1 和 128 之间")
    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public DcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DcTemplate template) {
        this.template = template;
    }

    @Length(min = 0, max = -1, message = "模板JSON长度必须介于 0 和 -1 之间")
    public String getTemplateJson() {
        return templateJson;
    }

    public void setTemplateJson(String templateJson) {
        this.templateJson = templateJson;
    }

    @NotNull(message = "项目不能为空")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean getReplace() {
        return replace;
    }

    public void setReplace(boolean replace) {
        this.replace = replace;
    }

    public List<DcTable> getDcTableList() {
        return dcTableList;
    }

    public void setDcTableList(List<DcTable> dcTableList) {
        this.dcTableList = dcTableList;
    }

    public List<DcTable> getChildren() { return children; }

    public void setChildren(List<DcTable> children) { this.children = children; }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "dc_scheme";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1339746483134570552";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
