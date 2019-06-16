package com.xyz.demo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("更新用户信息参数")
public class ReqUpdate {
    @ApiModelProperty("设备id")
    private String deviceToken;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("昵称")
    private String nickName;
}
