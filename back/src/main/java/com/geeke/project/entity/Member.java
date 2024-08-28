package com.geeke.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.admin.entity.User;
import com.geeke.common.persistence.DataEntity;
import com.geeke.project.entity.Project;
import javax.validation.constraints.NotNull;

/**
 * 项目管理Entity
 * @author
 * @version
 */

public class Member extends DataEntity<Member> {

    private static final long serialVersionUID = 1351544466239864851L;

    private Project project; // 项目

    private User user; // 用户

    // 构造方法
    public Member() {
        super();
    }

    public Member(String id) {
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

    @NotNull(message = "用户不能为空")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "prj_member";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1351544466239864851";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
