package com.lb.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 登录参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "LoginParamDTO", description = "登录参数")
public class LoginParamDTO {
    @ApiModelProperty(value = "登录类型")
    @NotEmpty(message = "登录类型不能为空")
    private String type;
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    private String account;
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
