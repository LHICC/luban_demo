package com.lb.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.lb.base.auth.server.utils.JwtTokenServerUtils;
import com.lb.base.auth.utils.JwtUserInfo;
import com.lb.base.auth.utils.Token;
import com.lb.common.exception.code.ExceptionCode;
import com.lb.common.vo.R;
import com.lb.user.dto.LoginDTO;
import com.lb.user.dto.ResourceQueryDTO;
import com.lb.user.dto.UserDTO;
import com.lb.user.entity.Resource;
import com.lb.user.entity.User;
import com.lb.user.ldap.Person;
import com.lb.user.ldap.PersonRepository;
import com.lb.user.service.ResourceService;
import com.lb.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 认证业务类
 */
@Service
@Slf4j
public class AuthManager {
    @Autowired
    private JwtTokenServerUtils jwtTokenServerUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;


    /**
     * 登陆
     *
     * @param account
     * @param password
     * @return
     */
    private R<LoginDTO> simpleLogin(String account, String password) {
        // 2. 验证登录
        R<User> result = this.getUser(account, password);
        if (result.getIsError()) {
            return R.fail(result.getCode(), result.getMsg());
        }
        User user = result.getData();

        return getLoginDTOR(user);
    }

    /**
     * 生成token
     *
     * @param user
     * @return
     */
    private Token getToken(User user) {
        JwtUserInfo userInfo = new JwtUserInfo(user.getId(), user.getAccount(), user.getName(), user.getOrgId(), user.getStationId());

        Token token = this.jwtTokenServerUtils.generateUserToken(userInfo, null);
        log.info("token={}", token.getToken());
        return token;
    }


    /**
     * 校验用户名密码并返回用户
     *
     * @param account
     * @param password
     * @return
     */
    private R<User> getUser(String account, String password) {
        User user = this.userService.getByAccount(account);

        // 密码错误
        String passwordMd5 = DigestUtils.md5Hex(password);
        if (user == null) {
            return R.fail(ExceptionCode.JWT_USER_INVALID);
        }
        if (!user.getPassword().equalsIgnoreCase(passwordMd5)) {
            return R.fail("用户名或密码错误!");
        }
        return R.success(user);
    }


    /**
     * 账号登录
     *
     * @param account
     * @param password
     * @return
     */
    public R<LoginDTO> login(String account, String password) {
        return this.simpleLogin(account, password);
    }

    /**
     * LDAP账号登录
     *
     * @param account
     * @param password
     * @return
     */
    public R<LoginDTO> loginLDAP(String account, String password) {
        // 判断用户名
        LdapQuery query = LdapQueryBuilder.query().where("commonName").is(account);

        Person person = null;
        try {
            person = ldapTemplate.findOne(query, Person.class);
        } catch (EmptyResultDataAccessException e) {
            // 如果没查到该用户名，则会抛出异常EmptyResultDataAccessException
            return R.fail("用户名或密码错误!");
        }

        // 判断用户密码（利用ldap的认证）
        EqualsFilter filter = new EqualsFilter("commonName", account);
        if (!ldapTemplate.authenticate("", filter.toString(), password)) {
            return R.fail("用户名或密码错误!");
        }

        // 查询用户信息
        User user = userService.getByLdapUid(person.getUidNumber());
        if (ObjectUtil.isEmpty(user)) {
            // TODO 初始化用户信息并设置角色为普通
            return R.fail("用户不存在");
        }

        return getLoginDTOR(user);
    }

    @NotNull
    private R<LoginDTO> getLoginDTOR(User user) {
        // 3, token
        Token token = this.getToken(user);

        // 缓存token 缓存时间要超过token 失效时间
        List<Resource> resourceList = this.resourceService.findVisibleResource(ResourceQueryDTO.builder().userId(user.getId()).build());
        List<String> permissionsList = resourceList.stream().filter(item -> item != null).map(Resource::getCode).collect(Collectors.toList());
        if (user.getId() < 10L) {
            log.info("超级管理员");
            permissionsList = resourceService.list().stream().filter(item -> item != null).map(Resource::getCode).collect(Collectors.toList());
        }
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        return R.success(LoginDTO.builder().user(userDTO).permissionsList(permissionsList).token(token).build());
    }

    @Autowired
    LdapTemplate ldapTemplate;
    @Autowired
    private PersonRepository personRepository;
}
