package cn.dpy.shiro.config;

import cn.dpy.shiro.constant.CommonStatus;
import cn.dpy.shiro.constant.SysConstant;
import cn.dpy.shiro.entity.AdminUser;
import cn.dpy.shiro.entity.SysPermission;
import cn.dpy.shiro.entity.SysRole;
import cn.dpy.shiro.service.AdminUserService;
import cn.dpy.shiro.service.SysPermissionService;
import cn.dpy.shiro.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: dupinyan
 * @Description: shiro核心配置
 * @Date: 2019/11/13 10:43
 * @Version: 1.0
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private AdminUserService adminService;


    /**
     * 权限验证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("----->权限验证---->");
        String currentUsername = (String) getAvailablePrincipal(principalCollection);
        log.info("doGetAuthorizationInfo,user:" + currentUsername);
        List<String> permissionList = new ArrayList<>();
        AdminUser admin = (AdminUser) getSession(SysConstant.SESSION_ADMIN);
        if (null == admin) {
            throw new AuthorizationException();
        }
        try {
            List<SysPermission> list;
            if ("root".equalsIgnoreCase(admin.getUsername())) {
                list = sysPermissionService.findAll();
            } else {
                SysRole sysRole = sysRoleService.findOne(admin.getRoleId());
                list = sysRole.getPermissions();
            }
            //获取当前用户权限列表
            list.forEach(x -> {
                if (!StringUtils.isEmpty(x.getName())) {
                    permissionList.add(x.getName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthorizationException();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        return info;
    }


    /**
     * 身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String password = String.valueOf(token.getPassword());
        String username = token.getUsername();

        AdminUser admin = null;

        AuthenticationInfo authInfo = new SimpleAuthenticationInfo(token.getUsername(), token.getCredentials(),this.getName());
        try {
            admin = adminService.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (admin == null) {
            throw new AuthenticationException("用户名或密码错误");
        } else if (admin.getStatus() == CommonStatus.ILLEGAL) {
            throw new AuthenticationException("该用户已被禁用");
        } else {
            // 添加登录日志
            adminService.update(new Date(), token.getHost(), admin.getId());
            setSession(SysConstant.SESSION_ADMIN, admin);
            return authInfo;
        }
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     *
     * @param key
     * @param value
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            session.setTimeout(1800000L);
            log.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (session != null) {
                session.setAttribute(key, value);
            }
        }
    }

    private Object getSession(String key) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            return currentUser.getSession().getAttribute(key);
        }
        return null;
    }
}
