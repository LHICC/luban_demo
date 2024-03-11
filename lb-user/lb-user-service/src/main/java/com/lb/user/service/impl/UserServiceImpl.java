package com.lb.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.user.entity.User;
import com.lb.user.mapper.UserMapper;
import com.lb.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 账号
 * </p>
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据账户获取用户信息
     *
     * @param account
     * @return
     */
    @Override
    public User getByAccount(String account) {
        return this.baseMapper.getByAccount(account);
    }

    /**
     * 根据ldapUid获取用户信息
     *
     * @param ldapUid
     * @return
     */
    @Override
    public User getByLdapUid(String ldapUid) {
         return this.baseMapper.getByLdapUid(ldapUid);
    }

}
