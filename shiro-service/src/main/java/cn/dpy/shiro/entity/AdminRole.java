package cn.dpy.shiro.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: dupinyan
 * @Description: 角色表
 * @Date: 2019/11/12 11:23
 * @Version: 1.0
 */
@Entity
@Table(name = "admin_role", schema = "shiro_demo", catalog = "")
public class AdminRole {

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
        AdminRole adminRole = (AdminRole) o;
        return id == adminRole.id &&
                Objects.equals(description, adminRole.description) &&
                Objects.equals(role, adminRole.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, description, role);
    }
}
