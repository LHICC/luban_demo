package com.lb.user.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lb.common.base.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DataScopeType", description = "数据权限类型-枚举")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DataScopeType implements BaseEnum {
    ALL(1, "全部"),
    THIS_LEVEL(2, "本级"),
    THIS_LEVEL_CHILDREN(3, "本级以及子级"),
    CUSTOMIZE(4, "自定义"),
    SELF(5, "个人");

    @ApiModelProperty("描述")
    private int val;
    private String desc;


    public static DataScopeType match(String val, DataScopeType def) {
        for (DataScopeType enm : DataScopeType.values()) {
            if (enm.name().equalsIgnoreCase(val)) {
                return enm;
            }
        }
        return def;
    }

    public static DataScopeType match(Integer val, DataScopeType def) {
        if (def == null) return def;
        for (DataScopeType enm : DataScopeType.values()) {
            if (val.equals(enm.getVal())) {
                return enm;
            }
        }
        return def;
    }

    @ApiModelProperty(
            value = "编码",
            allowableValues = "ALL,THIS_LEVEL,THIS_LEVEL_CHILDREN,CUSTOMIZE,SELF",
            example = "ALL"
    )
    public String getCode() {
        return this.name();
    }
}
