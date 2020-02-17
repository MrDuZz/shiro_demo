package cn.dpy.shiro.service.impl;

import cn.dpy.shiro.dao.SysPermissionDao;
import cn.dpy.shiro.entity.SysPermission;
import cn.dpy.shiro.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 15:25
 * @Version: 1.0
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService{

    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    public List<SysPermission> findAll() {
        return sysPermissionDao.findAll();
    }

    @Override
    public SysPermission findByPermissionName(String name) {
        return sysPermissionDao.findSysPermissionByName(name);
    }

    @Override
    public SysPermission findOne(Long id) {
        return sysPermissionDao.findById(id).get();
    }

    @Override
    public SysPermission save(SysPermission sysPermission) {
        return sysPermissionDao.save(sysPermission);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletes(Long[] ids) {
        for (long id : ids) {
            sysPermissionDao.deletePermission(id);
            sysPermissionDao.deleteById(id);
        }
    }

}