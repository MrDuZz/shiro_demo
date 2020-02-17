package cn.dpy.shiro.service;

import cn.dpy.shiro.controller.MessageResult;
import cn.dpy.shiro.entity.Menu;
import cn.dpy.shiro.entity.SysPermission;
import cn.dpy.shiro.entity.SysRole;

import java.util.List;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 15:31
 * @Version: 1.0
 */
public interface SysRoleService {

    List<SysRole> findAll();

    SysRole findOne(Long roleId);

    List<SysPermission> getPermissions(Long roleId);

    List<Menu> toMenus(List<SysPermission> sysPermissions, Long parentId);

    SysRole save(SysRole sysRole);

    List<SysPermission> getAllPermission();

    MessageResult deletes(Long id);
}
