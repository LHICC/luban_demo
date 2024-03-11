package com.lb.user.api.v1;

import com.lb.user.common.R;
import com.lb.user.api.v1.dto.RoleDTO;
import com.lb.user.api.v1.dto.RoleResourceDTO;
import com.lb.user.api.v1.hystrix.RoleFailback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色 API
 */
@FeignClient(value = "lb-user-server", fallback = RoleFailback.class, path = "/role")
public interface RoleApi {


    /**
     * 查询角色
     *
     * @return
     */
    @RequestMapping(value = "/findRoleByUserId/{id}", method = RequestMethod.GET)
    R<List<Long>> findRoleByUserId(@PathVariable("id") Long id);

    /**
     * 查询全部角色和资源
     *
     * @return
     */
    @RequestMapping(value = "/findAllRoles", method = RequestMethod.GET)
    R<List<RoleResourceDTO>> findAllRoles();

}
