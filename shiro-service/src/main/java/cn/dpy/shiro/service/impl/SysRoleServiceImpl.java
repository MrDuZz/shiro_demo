package cn.dpy.shiro.service.impl;

import cn.dpy.shiro.dao.SysRoleDao;
import cn.dpy.shiro.entity.Menu;
import cn.dpy.shiro.entity.SysPermission;
import cn.dpy.shiro.entity.SysRole;
import cn.dpy.shiro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
