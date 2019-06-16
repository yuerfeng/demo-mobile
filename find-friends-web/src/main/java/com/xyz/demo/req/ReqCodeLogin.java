package com.xyz.demo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("验证码登录请求参数")
public class ReqCodeLogin {
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("短信验证码")
    private String verifyCode;
}
