package com.lb.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lb.user.dto.ResourceQueryDTO;
import com.lb.user.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 资源
 * </p>
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 根据角色id查询资源
     *
     * @param id
     * @return
     */
    List<Resource> findResourceByRoleId(@Param("id") Long id);

    /**
     * 查询 拥有的资源
     *
     * @param resource
     * @return
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);
}
