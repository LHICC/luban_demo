package com.lb.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.user.dto.RoleResourceDTO;
import com.lb.user.entity.Role;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 角色
 * </p>
 */
public interface RoleService extends IService<Role> {


    List<Role> findRoleByUserId(Long userId);


    List<RoleResourceDTO> findAllRolesWithResource();

}
