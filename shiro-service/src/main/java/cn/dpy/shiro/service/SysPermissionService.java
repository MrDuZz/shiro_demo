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

    /**
     * 根据权限名字查询权限
     * @param name 权限名称
     * @return
     */
    SysPermission findByPermissionName(String name);

    /**
     * 根据id查询某个权限
     * @param id 权限id
     * @return
     */
    SysPermission findOne(Long id);

    /**
     * 保存单个
     * @param sysPermission
     * @return
     */
    SysPermission save(SysPermission sysPermission);

    /**
     * 批量删除权限
     * @param ids
     */
    void deletes(Long[] ids);
}
