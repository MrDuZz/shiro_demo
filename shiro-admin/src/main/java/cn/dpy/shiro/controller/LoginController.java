package cn.dpy.shiro.controller;

import cn.dpy.shiro.constant.SysConstant;
import cn.dpy.shiro.entity.AdminUser;
import cn.dpy.shiro.entity.Menu;
import cn.dpy.shiro.service.AdminUserService;
import cn.dpy.shiro.service.SysPermissionService;
import cn.dpy.shiro.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 17:11
 * @Version: 1.0
 */
@RestController
@RequestMapping("login")
@Slf4j
public class LoginController extends BaseController {

    @Value("${spark.system.md5.key}")
    private String md5Key;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private AdminUserService adminService;


    @RequestMapping(value = "/sign/in", method = RequestMethod.POST)
    public MessageResult doLogin(@RequestParam("username") String username,
                                 @RequestParam("password") String password, HttpServletRequest request) {
        try {
            log.info("md5Key {}", md5Key);

            String pass = getMd5(password + md5Key);
            //password = Encrypt.MD5(password + md5Key);
            UsernamePasswordToken token = new UsernamePasswordToken(username, pass, true);
            token.setHost(getRemoteIp(request));
            SecurityUtils.getSubject().login(token);
            AdminUser admin = (AdminUser) request.getSession().getAttribute(SysConstant.SESSION_ADMIN);
            //token.setRememberMe(true);

            //获取当前用户的菜单权限
            List<Menu> list;
            if ("root".equalsIgnoreCase(admin.getUsername())) {
                list = sysRoleService.toMenus(sysPermissionService.findAll(), 0L);
            } else {
                list = sysRoleService.toMenus(sysRoleService.getPermissions(admin.getRoleId()), 0L);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("permissions", list);
            map.put("admin", admin);
            return success("登录成功", map);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return error(e.getMessage());
        }
    }


    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping(value = "logout")
    @ResponseBody
    public MessageResult logout() {
        SecurityUtils.getSubject().logout();
        return success();
    }

    private String getMd5(String str) {
        MessageDigest md5 = null;
        byte[] digest = new byte[0];
        try {
            md5 = MessageDigest.getInstance("MD5");
            digest = md5.digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.info("----->md5加密异常---->");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }


    private String strToStr(String str, String defaultValue) {
        if (str != null && !str.isEmpty()) {
            defaultValue = str;
        }

        return defaultValue;
    }

}
