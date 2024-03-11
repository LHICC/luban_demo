package com.lb.product.config;


import cn.hutool.core.text.StrPool;
import com.lb.common.exception.code.ExceptionCode;
import com.lb.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@ResponseBody
@Configuration
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalExceptionCatch {

    @ExceptionHandler(DuplicateKeyException.class)
    public R<String> duplicateKeyException(DuplicateKeyException ex, HttpServletRequest request) {
        log.warn("DuplicateKeyException", ex);
        try {
            String[] exMessage = ex.getMessage().split("###");
            String message = exMessage[1].split("Duplicate entry '")[1].split("' for key")[0];
            message = message.split("-")[0]; // 联合唯一索引取第一个字段
            return R.result(ExceptionCode.ILLEGALA_ARGUMENT_EX.getCode(), "", message + " 已存在!").setPath(request.getRequestURI());
        } catch (Exception e) {
            return R.result(ExceptionCode.SQL_EX.getCode(), "", ExceptionCode.SQL_EX.getMsg()).setPath(request.getRequestURI());
        }
    }
}
