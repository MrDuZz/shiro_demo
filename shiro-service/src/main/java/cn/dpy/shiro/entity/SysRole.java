package cn.dpy.shiro.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @Author: dupinyan
 * @Description: 角色表
 * @Date: 2019/11/12 11:23
 * @Version: 1.0
 */
@Entity
@Table(name = "admin_role", schema = "shiro_demo", catalog = "")
public class SysRole {

    /**
     * 主键id
     */
    private long id;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 标识
     */
    private String role;

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="SysRolePermission",joinColumns={
            @JoinColumn(name="roleId")},inverseJoinColumns={
            @JoinColumn(name="permissionId")})
    private List<SysPermission> permissions;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="userId")})
    private List<AdminUser> users;// 一个角色对应多个用户

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<AdminUser> getUsers() {
        return users;
    }

    public void setUsers(List<AdminUser> users) {
        this.users = users;
    }

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 255)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRole adminRole = (SysRole) o;
        return id == adminRole.id &&
                Objects.equals(description, adminRole.description) &&
                Objects.equals(role, adminRole.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, description, role);
    }
}
