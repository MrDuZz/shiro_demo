package cn.dpy.shiro.dao;

import cn.dpy.shiro.dao.base.BaseDao;
import cn.dpy.shiro.entity.AdminUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 10:38
 * @Version: 1.0
 */
@Repository
public interface AdminUserDao extends BaseDao<AdminUser> {

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("update AdminUser a set a.lastLoginTime=?1,a.lastLoginIp=?2 where a.id=?3")
    int updateAdminLastTimeAndIp(Date date, String ip, Long adminId);

    AdminUser findAdminUserByUsernameAndPassword(String username, String password);
}
