package com.geeke.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.geeke.admin.entity.User;
import com.geeke.common.persistence.DataEntity;
import com.geeke.dbtype.entity.SysDbType;
import com.geeke.dc.entity.DcScheme;
import com.geeke.project.entity.Member;
import com.google.common.collect.Lists;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 项目管理Entity
 * @author
 * @version
 */

public class Project extends DataEntity<Project> {

    private static final long serialVersionUID = 1351544466239864850L;

    private User user; // 项目负责人

    private String name; // 名称

    private String status = "0"; // 状态

    private String dbUsername; // 数据库用户

    private String dbPassword; // 数据库密码

    private String dbUrl; // 数据库地址

    private String dbIp; // 数据库IP

    private String dbPort; // 数据库端口

    private String dbName; // 数据库名

    private SysDbType dbType; // 数据库类型

    private String cloudName; // 服务名

    private List<Member> memberList = Lists.newArrayList(); // 子表列表

    private List<DcScheme> children = Lists.newArrayList(); // 方案集合(使用级联二级三级保证命名相同)

    // 构造方法
    public Project() {
        super();
    }

    public Project(String id) {
        super(id);
    }

    // 生成get和set方法

    @NotNull(message = "项目负责人不能为空")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Length(min = 0, max = 128, message = "名称长度必须介于 0 和 128 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 1, message = "状态长度必须介于 1 和 1 之间")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Length(min = 0, max = 64, message = "数据库用户长度必须介于 0 和 64 之间")
    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    @Length(min = 0, max = 64, message = "数据库密码长度必须介于 0 和 64 之间")
    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    @Length(min = 0, max = 128, message = "数据库地址长度必须介于 0 和 128 之间")
    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Length(min = 1, max = 64, message = "数据库IP长度必须介于 1 和 64 之间")
    public String getDbIp() {
        return dbIp;
    }

    public void setDbIp(String dbIp) {
        this.dbIp = dbIp;
    }

    @Length(min = 1, max = 10, message = "数据库端口长度必须介于 1 和 10 之间")
    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    @Length(min = 1, max = 32, message = "数据库名长度必须介于 1 和 32 之间")
    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public SysDbType getDbType() {
        return dbType;
    }

    public void setDbType(SysDbType dbType) {
        this.dbType = dbType;
    }

    @Length(min = 1, max = 64, message = "服务名长度必须介于 1 和 64 之间")
    public String getCloudName() {
        return cloudName;
    }

    public void setCloudName(String cloudName) {
        this.cloudName = cloudName;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public List<DcScheme> getChildren() {
        return children;
    }

    public void setChildren(List<DcScheme> children) {
        this.children = children;
    }

    /**
     * 获取实体对应表名
     */
    @Override
    @JsonIgnore
    public String getBusTableName() {
        return "prj_project";
    }

    /**
     * 获取实体对应Id
     */
    @Override
    @JsonIgnore
    public String getBusTableId() {
        return "1351544466239864850";
    }

    /**
     * 获取实体表中是否存在del_flag字段
     */
    @JsonIgnore
    public boolean getBusTableHasDelFlag() {
        return true;
    }
}
