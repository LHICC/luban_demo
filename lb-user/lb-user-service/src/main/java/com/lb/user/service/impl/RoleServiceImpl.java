package com.lb.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.user.dto.RoleResourceDTO;
import com.lb.user.entity.Resource;
import com.lb.user.entity.Role;
import com.lb.user.mapper.RoleMapper;
import com.lb.user.service.ResourceService;
import com.lb.user.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 业务实现类
 * 角色
 * </p>
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private ResourceService resourceService;

    /**
     * 根据用户id查询角色集合
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> findRoleByUserId(Long userId) {
        return baseMapper.findRoleByUserId(userId);
    }

    @Override
    public List<RoleResourceDTO> findAllRolesWithResource() {
        List<RoleResourceDTO> roleResourceDTOS = new ArrayList<>();
        List<Role> roles = this.list();
        for (Role role : roles) {
            Long id = role.getId();
            List<Resource> resources = resourceService.findResourceByRoleId(id);
            roleResourceDTOS.add(new RoleResourceDTO(role, resources));
        }
        return roleResourceDTOS;
    }

}
