package cn.dpy.shiro.entity;

import lombok.Data;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    private Long id;

    /**
     * 权限标题
     */
    @NotBlank(message = "权限名不能为空")
    @NotNull(message = "权限名不能为空")
    private String title;

    /**
     * 权限名称字符串
     */
    @NotBlank(message = "权限名不能为空")
    @NotNull(message = "权限名不能为空")
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
    @JoinTable(name = "admin_role_permission", joinColumns = {
            @JoinColumn(name = "rule_id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id")})
    private List<SysRole> roles;

}
