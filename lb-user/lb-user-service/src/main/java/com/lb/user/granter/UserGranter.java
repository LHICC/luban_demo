package com.lb.user.granter;

import com.lb.common.vo.R;
import com.lb.user.dto.LoginDTO;
import com.lb.user.dto.LoginParamDTO;

/**
 * 抽象策略类
 */
public interface UserGranter {

    /**
     * 获取数据
     *
     * @param loginReq 传入的参数
     * @return map值
     */
    R<LoginDTO> login(LoginParamDTO dto);
}