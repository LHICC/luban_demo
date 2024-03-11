package com.lb.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.user.dto.ResourceQueryDTO;
import com.lb.user.entity.Resource;
import com.lb.user.mapper.ResourceMapper;
import com.lb.user.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 资源
 * </p>
 */
@Slf4j
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {


    /**
     * 根据角色id查询资源
     *
     * @param id
     * @return
     */
    @Override
    public List<Resource> findResourceByRoleId(Long id) {
        return baseMapper.findResourceByRoleId(id);
    }
    /**
     * 查询用户的可用资源
     * 需要清除 USER_MENU 缓存
     *
     * @param resource
     * @return
     */
    @Override
    public List<Resource> findVisibleResource(ResourceQueryDTO resource) {
        List<Resource> resourceList = baseMapper.findVisibleResource(resource);

        if (resource.getMenuId() == null) {
            return resourceList;
        }

        // 根据查询条件过滤数据
        return resourceList.stream()
                .filter((re) -> Objects.equals(resource.getMenuId(), re.getMenuId()))
                .collect(Collectors.toList());
    }

}
