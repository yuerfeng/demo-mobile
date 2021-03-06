package com.xyz.demo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("发送验证码请求参数")
public class ReqGetVerifyCode{
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("验证码类型0-注册,1修改密码,2短信登录")
    private Integer type;
}
