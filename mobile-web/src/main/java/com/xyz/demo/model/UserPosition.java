package com.xyz.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户位置")
public class UserPosition {
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("用户id")
    private String id;
    @ApiModelProperty("用户位置")
    private Position position;
}
