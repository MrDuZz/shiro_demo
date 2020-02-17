package cn.dpy.shiro.service.impl;

import cn.dpy.shiro.dao.AdminUserDao;
import cn.dpy.shiro.entity.AdminUser;
import cn.dpy.shiro.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2020/2/17 10:16
 * @Version: 1.0
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    AdminUserDao adminUserDao;

    @Override
    public List<AdminUser> findAll() {
        return adminUserDao.findAll();
    }

}
