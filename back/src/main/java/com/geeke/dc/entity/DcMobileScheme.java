package com.geeke.dc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.common.persistence.DataEntity;
import com.geeke.dc.entity.DcScheme;
import com.geeke.dc.entity.DcTemplate;
import com.geeke.project.entity.Project;
import com.google.common.collect.Lists;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 移动端方案Entity
 * @author
 * @version
 */

public class DcMobileScheme extends DataEntity<DcMobileScheme> {

    private static final long serialVersionUID = 1440433057862000819L;

    private Project project; // 项目

    private String name; // 名称

    private DcScheme scheme; // 后端方案

    private DcTemplate template; // 模板

    private String templateJson; // 模板json

    // 构造方法
    public DcMobileScheme() {
        super();
    }

    public DcMobileScheme(String id) {
        super(id);
    }

    // 生成get和set方法

    @NotNull(message = "项目不能为空")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Length(min = 1, max = 128, message = "名称长度必须介于 1 和 128 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "后端方案不能为空")
    public DcScheme getScheme() {
        return scheme;
    }

    public void setScheme(DcScheme scheme) {
        this.scheme = scheme;
    }

    public DcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DcTemplate template) {
        this.template = template;
    }

    @Length(min = 0, max = -1, message = "模板json长度必须介于 0 和 -1 之间")
    public String getTemplateJson() {
        return templateJson;
    }

    public void setTemplateJson(String templateJson) {
        this.templateJson = templateJson;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "dc_mobile_scheme";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1440433057862000819";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
