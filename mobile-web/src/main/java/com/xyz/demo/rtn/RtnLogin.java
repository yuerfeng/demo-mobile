package com.xyz.demo.rtn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登录返回值")
public class RtnLogin {
    @ApiModelProperty("登录产生的token")
    private String token;
}
