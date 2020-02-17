package cn.dpy.shiro.controller;

import cn.dpy.shiro.entity.AdminUser;
import cn.dpy.shiro.service.TestService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2020/2/17 10:15
 * @Version: 1.0
 */
@RestController
@RequestMapping("test")
public class TestController extends BaseController {


    @Autowired
    private TestService testService;


    @RequiresPermissions("invite/management:query")
    @RequestMapping("testPermission")
    public MessageResult findAll() {
        List<AdminUser> adminUserList = testService.findAll();
        return success(adminUserList);
    }


}
