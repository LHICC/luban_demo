package com.lb.user.granter;

import com.lb.common.vo.R;
import com.lb.user.dto.LoginDTO;
import com.lb.user.dto.LoginParamDTO;
import com.lb.user.entity.User;
import com.lb.user.ldap.Person;
import com.lb.user.ldap.PersonRepository;
import com.lb.user.service.UserService;
import com.lb.user.service.impl.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 策略：LDAP登录
 **/
@Component
public class LdapGranter implements UserGranter {

    @Autowired
    private AuthManager authManager;

    @Override
    public R<LoginDTO> login(LoginParamDTO dto) {
        System.out.println("登录方式为LDAP登录" + dto);
        return authManager.loginLDAP(dto.getAccount(), dto.getPassword());
    }

}
