package cn.dpy.shiro.entity;

import lombok.Data;

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
@Data
@Table(name = "admin_role", schema = "shiro_demo", catalog = "")
public class SysRole {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinTable(name = "admin_role_permission", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "rule_id", referencedColumnName = "id")})
    private List<SysPermission> permissions;

//    // 用户 - 角色关系定义;
//    @ManyToMany
//    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="userId")})
//    private List<AdminUser> users;// 一个角色对应多个用户

}
