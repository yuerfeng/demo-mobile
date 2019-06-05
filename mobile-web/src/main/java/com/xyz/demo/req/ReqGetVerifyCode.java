package com.xyz.demo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("发送验证码请求参数")
public class ReqGetVerifyCode extends ReqBase{
    @ApiModelProperty("手机号")
    private String mobile;
}
