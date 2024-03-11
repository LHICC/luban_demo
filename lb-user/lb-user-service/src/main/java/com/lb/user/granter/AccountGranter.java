package com.lb.user.granter;

import com.lb.common.vo.R;
import com.lb.user.dto.LoginDTO;
import com.lb.user.dto.LoginParamDTO;
import com.lb.user.service.impl.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 策略：账号登录
 **/
@Component
public class AccountGranter implements UserGranter {
    @Autowired
    private AuthManager authManager;

    @Override
    public R<LoginDTO> login(LoginParamDTO dto) {
        System.out.println("登录方式为账号登录" + dto);
        return authManager.login(dto.getAccount(), dto.getPassword());
    }
}