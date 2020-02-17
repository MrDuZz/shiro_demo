package cn.dpy.shiro.dao;

import cn.dpy.shiro.dao.base.BaseDao;
import cn.dpy.shiro.entity.SysPermission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 10:39
 * @Version: 1.0
 */
@Repository
public interface SysPermissionDao extends BaseDao<SysPermission> {

    /**
     * 根据权限名称查询权限
     * @param name 权限名称
     * @return
     */
    SysPermission findSysPermissionByName(String name);

    @Transactional
    @Modifying
    @Query(value="delete from admin_role_permission where rule_id = ?1",nativeQuery = true)
    int deletePermission(long permissionId);
}
