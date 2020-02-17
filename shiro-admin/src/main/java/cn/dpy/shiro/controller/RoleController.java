package cn.dpy.shiro.controller;

import cn.dpy.shiro.entity.Menu;
import cn.dpy.shiro.entity.SysRole;
import cn.dpy.shiro.service.SysRoleService;
import cn.dpy.shiro.utils.BindingResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2020/2/17 11:41
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "system/role")
public class RoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequiresPermissions("system:role:merge")
    @RequestMapping("merge")
    @Transactional(rollbackFor = Exception.class)
    public MessageResult mergeRole(@Valid SysRole sysRole, BindingResult bindingResult) {
        MessageResult result = BindingResultUtil.validate(bindingResult);
        if (result != null) {
            return result;
        }
        sysRole = sysRoleService.save(sysRole);
        if (sysRole != null) {
            result = success("操作成功");
            result.setData(sysRole);
            return result;
        } else {
            return MessageResult.error(500, "操作失败");
        }

    }

    /**
     * 全部权限树
     *
     * @return
     */
    @RequiresPermissions("system:role:permission:all")
    @RequestMapping("permission/all")
    public MessageResult allMenu() {
        List<Menu> list = sysRoleService.toMenus(sysRoleService.getAllPermission(), 0L);
        MessageResult result = success("success");
        result.setData(list);
        return result;
    }

    /**
     * 角色拥有的权限
     *
     * @param roleId
     * @return
     */
    @RequiresPermissions("system:role:permission")
    @RequestMapping("permission")
    public MessageResult roleAllPermission(Long roleId) {
        List<Menu> content = sysRoleService.toMenus(sysRoleService.findOne(roleId).getPermissions(), 0L);
        MessageResult result = success();
        result.setData(content);
        return result;
    }

    /**
     * 更改角色权限
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    //@RequiresPermissions("system:role:permission:update")
   /* @RequestMapping("permission/update")
    @Transactional(rollbackFor = Exception.class)
    @AccessLog(module = AdminModule.SYSTEM, operation = "更改角色拥有的权限Menu")
    public MessageResult updateRolePermission(Long roleId, Long[] permissionId) {
        SysRole sysRole = sysRoleService.findOne(roleId);
        if (permissionId != null) {
            List<SysPermission> list = Arrays.stream(permissionId)
                    .map(x -> sysPermissionService.findOne(x))
                    .collect(Collectors.toList());
            sysRole.setPermissions(list);
        } else {
            sysRole.setPermissions(null);
        }
        return success("操作成功");
    }*/

//    /**
//     * 全部角色
//     *
//     * @return
//     */
//    @RequiresPermissions("system:role:all")
//    @RequestMapping("all")
//    public MessageResult getAllRole(PageModel pageModel) {
//        Page<SysRole> all = sysRoleService.findAll(null, pageModel.getPageable());
//        return success(all);
//    }

    /**
     * 删除角色
     *
     * @return
     */
    @RequiresPermissions("system:role:deletes")
    @RequestMapping("deletes")
    public MessageResult deletes(Long id) {
        MessageResult result = sysRoleService.deletes(id);
        return result;
    }

}
