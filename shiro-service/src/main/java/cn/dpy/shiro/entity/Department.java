package cn.dpy.shiro.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author: dupinyan
 * @Description: 部门配置
 * @Date: 2019/11/12 11:23
 * @Version: 1.0
 */
@Entity
@Data
public class Department {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 部门领导id
     */
    private Long leaderId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

}
