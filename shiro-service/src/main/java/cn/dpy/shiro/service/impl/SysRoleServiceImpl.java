package cn.dpy.shiro.service.impl;

import cn.dpy.shiro.controller.MessageResult;
import cn.dpy.shiro.dao.AdminUserDao;
import cn.dpy.shiro.dao.SysPermissionDao;
import cn.dpy.shiro.dao.SysRoleDao;
import cn.dpy.shiro.entity.AdminUser;
import cn.dpy.shiro.entity.Menu;
import cn.dpy.shiro.entity.SysPermission;
import cn.dpy.shiro.entity.SysRole;
import cn.dpy.shiro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 15:31
 * @Version: 1.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysPermissionDao permissionDao;

    @Autowired
    private AdminUserDao adminDao;

    @Override
    public List<SysPermission> getPermissions(Long roleId) {
        SysRole sysRole = findOne(roleId);
        return sysRole.getPermissions();
    }

    @Override
    public List<SysRole> findAll() {
        return sysRoleDao.findAll();
    }

    @Override
    public SysRole findOne(Long roleId) {
        return sysRoleDao.findById(roleId).get();
    }


    /**
     * 把权限转换成菜单树
     *
     * @param sysPermissions
     * @param parentId
     * @return
     */
    @Override
    public List<Menu> toMenus(List<SysPermission> sysPermissions, Long parentId) {
        return sysPermissions.stream()
                .filter(x -> x.getParentId().equals(parentId))
                .sorted(Comparator.comparing(SysPermission::getSort))
                .map(x ->
                        Menu.builder()
                                .id(x.getId())
                                .name(x.getName())
                                .parentId(x.getParentId())
                                .sort(x.getSort())
                                .title(x.getTitle())
                                .description(x.getDescription())
                                .subMenu(toMenus(sysPermissions, x.getId()))
                                .build()

                )
                .collect(Collectors.toList());
    }

    @Override
    public SysRole save(SysRole sysRole) {
        return sysRoleDao.save(sysRole);
    }

    @Override
    public List<SysPermission> getAllPermission() {
        return permissionDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MessageResult deletes(Long id) {
        List<AdminUser> list = adminDao.findAllByRoleId(id);
        if (list != null && list.size() > 0) {
            return MessageResult.error("删除失败，请先删除该角色下的所有用户");
        }
        sysRoleDao.deleteById(id);
        return MessageResult.success("删除成功");
    }
}
