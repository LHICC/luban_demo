package com.lb.gateway.service;


import com.lb.user.api.v1.dto.RoleResourceDTO;

import java.util.List;
import java.util.concurrent.Future;

public interface RoleAuthService {

    Future<List<RoleResourceDTO>> findAllRoles();

    Future<List<Long>> findRoleByUserId(Long id);
}
