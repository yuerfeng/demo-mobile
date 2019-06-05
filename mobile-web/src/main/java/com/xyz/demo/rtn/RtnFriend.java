package com.xyz.demo.rtn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("好友信息")
public class RtnFriend {
    @ApiModelProperty("好友用户id")
    private Integer id;
    @ApiModelProperty("好用姓名")
    private String name;
    @ApiModelProperty("好友昵称")
    private String nickName;
}
