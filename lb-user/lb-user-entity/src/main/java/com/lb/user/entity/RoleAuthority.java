package com.lb.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lb.common.base.entity.SuperEntity;
import com.lb.user.enumeration.AuthorizeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 实体类
 * 角色的资源
 * </p>
 *
 */
@Data
// @SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("role_authority")
@ApiModel(value = "RoleAuthority", description = "角色的资源")
public class RoleAuthority extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id")
    @NotNull(message = "资源id不能为空")
    @TableField("authority_id")
    private Long authorityId;

    /**
     * 权限类型
     * #AuthorizeType{MENU:菜单;RESOURCE:资源;}
     */
    @ApiModelProperty(value = "权限类型")
    @NotNull(message = "权限类型不能为空")
    @TableField("authority_type")
    private AuthorizeType authorityType;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    @NotNull(message = "角色id不能为空")
    @TableField("role_id")
    private Long roleId;

}
