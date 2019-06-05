package com.xyz.demo.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ApiModel("注册用户参数")
public class ReqUser {
    private String mobile;
    private String password;
    private String deviceToken;
    private String name;
    private String nickName;
    @ApiModelProperty("验证码")
    private String verifyCode;
}
