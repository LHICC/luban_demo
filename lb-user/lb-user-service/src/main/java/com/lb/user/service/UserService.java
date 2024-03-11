package com.lb.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.user.entity.User;


/**
 * <p>
 * 业务接口
 * 账号
 * </p>
 */
public interface UserService extends IService<User> {

    User getByAccount(String account);

    User getByLdapUid(String ldapUid);
}
