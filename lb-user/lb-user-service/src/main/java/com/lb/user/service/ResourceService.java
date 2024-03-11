package com.lb.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.user.dto.ResourceQueryDTO;
import com.lb.user.entity.Resource;

import java.util.List;


/**
 * <p>
 * 业务接口
 * 资源
 * </p>
 */
public interface ResourceService extends IService<Resource> {

    List<Resource> findResourceByRoleId(Long id);

    List<Resource> findVisibleResource(ResourceQueryDTO resource);
}
