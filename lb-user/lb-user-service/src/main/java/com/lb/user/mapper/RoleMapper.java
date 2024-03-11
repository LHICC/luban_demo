package com.lb.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lb.user.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 角色
 * </p>
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 查询用户拥有的角色
     *
     * @param userId
     * @return
     */
    List<Role> findRoleByUserId(@Param("userId") Long userId);

}
