package cn.dpy.shiro.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @Author: dupinyan
 * @Description: 权限配置类
 * @Date: 2019/11/12 11:23
 * @Version: 1.0
 */
@Entity
@Data
@Table(name = "admin_permission", schema = "shiro_demo", catalog = "")
public class SysPermission {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 权限标题
     */
    private String title;

    /**
     * 权限名称字符串
     */
    private String name;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;

    //角色 -- 权限关系：多对多关系;
    @ManyToMany
    @JoinTable(name="admin_role_permission",joinColumns={
            @JoinColumn(name="rule_id")},inverseJoinColumns={
            @JoinColumn(name="role_id")})
    private List<SysRole> roles;

}
