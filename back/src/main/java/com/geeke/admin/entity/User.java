package com.geeke.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.geeke.admin.entity.Role;
import com.geeke.common.IUser;
import com.geeke.common.persistence.DataEntity;
import com.google.common.collect.Lists;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 开发者管理Entity
 * @author
 * @version
 */

public class User extends DataEntity<User> implements IUser {

    private static final long serialVersionUID = 1289059804542828547L;

    private Role role; // 角色

    private String name; // 名称

    private String loginName; // 登录账号

    // 密码字段
    private boolean loginPasswordUpdate; // 是否修改 密码
    private String loginPassword; // 密码
    // 其它字段

    private String isLocked; // 禁用

    private String email; // 邮箱地址

    private String organ; // 组织机构

    // 构造方法
    public User() {
        super();
    }

    public User(String id) {
        super(id);
    }

    // 生成get和set方法

    @NotNull(message = "角色不能为空")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Length(min = 1, max = 128, message = "名称长度必须介于 1 和 128 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 10, message = "登录账号长度必须介于 1 和 10 之间")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @JsonIgnore
    public String getLoginPassword() {
        return loginPassword;
    }

    @JsonDeserialize
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public boolean getLoginPasswordUpdate() {
        return loginPasswordUpdate;
    }

    public void setLoginPasswordUpdate(boolean loginPasswordUpdate) {
        this.loginPasswordUpdate = loginPasswordUpdate;
    }

    @NotNull(message = "禁用不能为空")
    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    @Length(min = 1, max = 36, message = "邮箱地址长度必须介于 1 和 36 之间")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 1, max = 1024, message = "组织机构长度必须介于 1 和 1024 之间")
    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "sys_user";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1289059804542828547";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
