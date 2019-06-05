package com.xyz.demo.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ApiModel("注册用户参数")
public class ReqRegist {
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("设备id")
    private String deviceToken;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("验证码")
    private String verifyCode;
}
