package cn.dpy.shiro.entity;

import cn.dpy.shiro.constant.CommonStatus;
import lombok.Data;

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
@Data
@Table(name = "admin_user", schema = "shiro_demo", catalog = "")
public class AdminUser {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
//    @JoinTable(name = "SysUserRole", joinColumns = {
//            @JoinColumn(name = "id") }, inverseJoinColumns ={
//            @JoinColumn(name = "roleId") })
//    private List<SysRole> roleList;// 一个用户具有多个角色

}
