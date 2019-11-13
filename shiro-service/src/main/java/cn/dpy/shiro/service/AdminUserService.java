package cn.dpy.shiro.service;

import cn.dpy.shiro.entity.AdminUser;

import java.util.Date;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 15:32
 * @Version: 1.0
 */
public interface AdminUserService {

    AdminUser login(String username, String password);

    int update(Date date, String host, Long id);
}
