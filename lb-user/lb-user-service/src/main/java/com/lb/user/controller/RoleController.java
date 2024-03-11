package com.lb.user.controller;


import com.lb.common.base.BaseController;
import com.lb.common.vo.R;
import com.lb.user.dto.RoleResourceDTO;
import com.lb.user.entity.Role;
import com.lb.user.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * 角色
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/role")
@Api(value = "Role", tags = "角色")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;


    // 提供给gateway
    @ApiOperation(value = "查询角色", notes = "查询角色")
    @GetMapping("/findRoleByUserId/{id}")
    public R<List<Long>> findRoleByUserId(@PathVariable("id") Long id) {
        List<Role> roles = roleService.findRoleByUserId(id);
        return success(roles.stream().map(Role::getId).collect(Collectors.toList()));
    }

    // 提供给gateway
    @ApiOperation(value = "查询全部角色", notes = "查询全部角色")
    @GetMapping("/findAllRoles")
    public R<List<RoleResourceDTO>> findAllRolesWithResource() {
        log.info("查询角色");
        List<RoleResourceDTO> roles = roleService.findAllRolesWithResource();
        return success(roles);
    }
}
