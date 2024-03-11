package com.lb.gateway.controller;

import com.lb.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DefaultHystrixController {

    @RequestMapping(value = "/fallback", method = RequestMethod.GET)
    public R fallback() {
        log.info("服务异常，已熔断！");
        return R.fail("服务异常，请稍后重试！");
    }

}
