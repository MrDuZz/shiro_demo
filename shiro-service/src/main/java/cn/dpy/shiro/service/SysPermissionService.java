package cn.dpy.shiro.service;

import cn.dpy.shiro.entity.SysPermission;

import java.util.List;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 15:26
 * @Version: 1.0
 */
public interface SysPermissionService {

    List<SysPermission> findAll();
}
