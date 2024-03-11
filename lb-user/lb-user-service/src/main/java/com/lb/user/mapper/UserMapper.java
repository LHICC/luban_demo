package com.lb.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lb.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 账号
 * </p>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    /**
     * 根据账号查询用户
     *
     * @param account
     * @return
     */
    User getByAccount(@Param("account") String account);

    /**
     * 根据ldapUid查询用户
     *
     * @param ldapUid
     * @return
     */
    User getByLdapUid(@Param("ldapUid") String ldapUid);
}
