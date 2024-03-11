package com.lb.gateway.service.impl;

// import com.itheima.authority.api.v1.RoleApi;
// import com.itheima.authority.api.v1.dto.RoleResourceDTO;
// import com.itheima.authority.common.R;
// import com.itheima.gateway.service.RoleAuthService;

import com.lb.gateway.service.RoleAuthService;
import com.lb.user.api.v1.RoleApi;
import com.lb.user.api.v1.dto.RoleResourceDTO;
import com.lb.user.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@Slf4j
@Service
public class RoleAuthServiceImpl implements RoleAuthService {

    @Lazy // feign与gateway版本兼容问题 需要使用Lazy
    @Autowired
    RoleApi roleApi;

    @Override
    @Async
    @Cacheable(value = "RoleAuthServiceImpl", key = "'findAllRoles'")
    public Future<List<RoleResourceDTO>> findAllRoles() {
        List<RoleResourceDTO> list;
        R<List<RoleResourceDTO>> allRoles = roleApi.findAllRoles();
        log.info("allRoles:{}", allRoles);
        list = allRoles.getData();
        if (list == null) {
            list = Collections.EMPTY_LIST;
        }
        return new AsyncResult<>(list);
    }

    @Override
    @Async
    @Cacheable(value = "RoleAuthServiceImpl", key = "'findRoleByUserId_'+#id")
    public Future<List<Long>> findRoleByUserId(Long id) {
        List<Long> list;
        R<List<Long>> roles = roleApi.findRoleByUserId(id);
        log.info("role:{}", roles);
        list = roles.getData();
        if (list == null) {
            list = Collections.EMPTY_LIST;
        }
        return new AsyncResult<>(list);
    }
}
