package cn.dpy.shiro.controller;

import cn.dpy.shiro.entity.AdminUser;
import cn.dpy.shiro.entity.Department;
import cn.dpy.shiro.service.DepartmentService;
import cn.dpy.shiro.utils.BindingResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2020/2/17 12:00
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/employee")
public class AdminUserController extends BaseController {

    @Autowired
    DepartmentService departmentService;

//    @RequiresPermissions("system:employee:merge")
//    @RequestMapping(value = "/merge")
//    @ResponseBody
//    @Transactional(rollbackFor = Exception.class)
//    public MessageResult addAdmin(AdminUser admin,
//                                  @RequestParam("departmentId") Long departmentId,
//                                  BindingResult bindingResult) {
//        MessageResult result = BindingResultUtil.validate(bindingResult);
//        if (result != null) {
//            return result;
//        }
//        Assert.notNull(departmentId, "请选择部门");
//        Department department = departmentService.findOne(departmentId);
//        admin.setDepartment(department);
//        String password;
//        if (admin.getId() != null) {
//            Admin admin1 = adminService.findOne(admin.getId());
//            admin.setLastLoginIp(admin1.getLastLoginIp());
//            admin.setLastLoginTime(admin1.getLastLoginTime());
//            //如果密码不为null更改密码
//            if (StringUtils.isNotBlank(admin.getPassword())) {
//                password = Encrypt.MD5(admin.getPassword() + md5Key);
//            } else {
//                password = admin1.getPassword();
//            }
//        } else {
//            //这里是新增
//            AdminUser newAdmin = adminService.findByUsername(admin.getUsername());
//            if (newAdmin != null) {
//                return error("用户名已存在！");
//            }
//            if (StringUtils.isBlank(admin.getPassword())) {
//                return error("密码不能为空");
//            }
//            password = Encrypt.MD5(admin.getPassword() + md5Key);
//        }
//        if("1".equalsIgnoreCase(admin.getAdminType())){
//            admin.setRoleId(20L);
//        }
//
//        admin.setPassword(password);
//        adminService.saveAdmin(admin);
//        return success("操作成功");
//    }
}
