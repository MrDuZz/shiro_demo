package cn.dpy.shiro.service.impl;

import cn.dpy.shiro.dao.AdminUserDao;
import cn.dpy.shiro.entity.AdminUser;
import cn.dpy.shiro.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 15:32
 * @Version: 1.0
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser login(String username, String password) {
        AdminUser admin = adminUserDao.findAdminUserByUsernameAndPassword(username, password);
        return admin;
    }

    @Override
    public int update(Date date, String host, Long id) {
        return adminUserDao.updateAdminLastTimeAndIp(date, host, id);
    }
}
