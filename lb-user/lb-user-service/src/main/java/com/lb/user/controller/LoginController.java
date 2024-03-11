package com.lb.user.controller;


import com.lb.common.exception.BizException;
import com.lb.common.vo.R;
import com.lb.user.dto.LoginDTO;
import com.lb.user.dto.LoginParamDTO;
import com.lb.user.granter.UserGranter;
import com.lb.user.granter.UserLoginFactory;
import com.lb.user.service.impl.AuthManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 客户端获取token
 * jwt token管理
 */
@RestController
@RequestMapping("/anno")
@Api(value = "UserAuthController", tags = "登录")
@Slf4j
public class LoginController {

    @Autowired
    private UserLoginFactory factory;

    /**
     * 刷新token
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "仅供测试使用", notes = "仅供测试使用")
    @GetMapping(value = "/token")
    @Deprecated
    public R<LoginDTO> tokenTx(LoginParamDTO dto) throws BizException {

        UserGranter granter = factory.getGranter(dto.getType());
        if (granter == null) {
            return R.fail("系统异常！");
        }
        return granter.login(dto);
    }

}
