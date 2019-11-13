package cn.dpy.shiro.entity;

import cn.dpy.shiro.constant.CommonStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * @Author: dupinyan
 * @Description: 后台用户表
 * @Date: 2019/11/12 11:23
 * @Version: 1.0
 */
@Entity
@Table(name = "admin_user", schema = "shiro_demo", catalog = "")
public class AdminUser {

    /**
     * 主键id
     */
    private long id;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 最后登陆ip
     */
    private String lastLoginIp;

    /**
     * 最后登陆时间
     */
    private Timestamp lastLoginTime;

    /**
     * 用户手机号
     */
    private String mobilePhone;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 角色id
     */
    private long roleId;

    /**
     * 用户状态 0-正常 1-非法
     */
    private CommonStatus status;

    /**
     * 用户名
     */
    private String username;

    /**
     * 部门id
     */
    private Long departmentId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 备注
     */
    private String remark;

    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SysUserRole", joinColumns = {
            @JoinColumn(name = "id") }, inverseJoinColumns ={
            @JoinColumn(name = "roleId") })
    private List<SysRole> roleList;// 一个用户具有多个角色

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "last_login_ip", nullable = true, length = 255)
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @Basic
    @Column(name = "last_login_time", nullable = true)
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Basic
    @Column(name = "mobile_phone", nullable = false, length = 255)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "role_id", nullable = false)
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "department_id", nullable = true)
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminUser adminUser = (AdminUser) o;
        return id == adminUser.id &&
                roleId == adminUser.roleId &&
                Objects.equals(email, adminUser.email) &&
                Objects.equals(lastLoginIp, adminUser.lastLoginIp) &&
                Objects.equals(lastLoginTime, adminUser.lastLoginTime) &&
                Objects.equals(mobilePhone, adminUser.mobilePhone) &&
                Objects.equals(password, adminUser.password) &&
                Objects.equals(status, adminUser.status) &&
                Objects.equals(username, adminUser.username) &&
                Objects.equals(departmentId, adminUser.departmentId) &&
                Objects.equals(createTime, adminUser.createTime) &&
                Objects.equals(remark, adminUser.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, lastLoginIp, lastLoginTime, mobilePhone, password, roleId, status, username, departmentId, createTime, remark);
    }
}
