package cn.dpy.shiro.controller;

import cn.dpy.shiro.entity.SysPermission;
import cn.dpy.shiro.service.SysPermissionService;
import com.mysema.commons.lang.Assert;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: dupinyan
 * @Description: 权限crud
 * @Date: 2020/2/17 10:25
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {

    @Autowired
    private SysPermissionService sysPermissionService;


    /**
     * 新增权限
     * @param permission
     * @return
     */
    @RequiresPermissions("system:permission:merge")
    @RequestMapping(value = "/merge", method = RequestMethod.POST)
    public MessageResult merge(@Valid SysPermission permission) {
        if (permission.getId() == null) {
            SysPermission data = sysPermissionService.findByPermissionName(permission.getName());
            if (data != null) {
                return error("权限名重复");
            }
        } else {
            SysPermission data = sysPermissionService.findOne(permission.getId());
            if (!data.getName().equalsIgnoreCase(permission.getName())) {
                SysPermission s = sysPermissionService.findByPermissionName(permission.getName());
                if (s != null) {
                    return error("权限名重复");
                }
            }
        }
        permission = sysPermissionService.save(permission);
        MessageResult result = success("保存权限成功");
        result.setData(permission);
        return result;
    }


    /**
     * 查询单个权限详情
     * @param permission
     * @return
     */
    @RequiresPermissions("system:permission:detail")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public MessageResult queryDetail( SysPermission permission) {
        permission = sysPermissionService.findByPermissionName(permission.getName());
        return success(permission);
    }



//    @RequiresPermissions("system:permission:page-query")
//    @RequestMapping(value = "/page-query", method = RequestMethod.POST)
//    public MessageResult pageQuery() {
//    }

    /**
     * 查询单个权限
     * @param id 权限id
     * @return
     */
    @RequiresPermissions("system:permission:detail")
    @PostMapping("/detail")
    public MessageResult detail(@RequestParam(value = "id") Long id) {
        SysPermission sysPermission = sysPermissionService.findOne(id);
        Assert.notNull(sysPermission, "该权限不存在");
        return MessageResult.getSuccessInstance("查询权限成功", sysPermission);
    }

    /**
     * 批量删除权限
     * @param ids
     * @return
     */
    @RequiresPermissions("system:permission:deletes")
    @PostMapping("/deletes")
    public MessageResult deletes(@RequestParam(value = "ids") Long[] ids) {
        sysPermissionService.deletes(ids);
        return MessageResult.success("批量删除权限成功");
    }

}
