package com.lb.user.api.v1.hystrix;

import com.lb.user.api.v1.RoleApi;
import com.lb.user.common.R;
import com.lb.user.api.v1.dto.RoleResourceDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleFailback implements RoleApi {

    @Override
    public R<List<Long>> findRoleByUserId(Long id) {
        return R.timeout();
    }

    @Override
    public R<List<RoleResourceDTO>> findAllRoles() {
        return R.timeout();
    }

}
