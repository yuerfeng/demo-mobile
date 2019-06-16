package com.xyz.demo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询用户位置信息")
public class ReqQueryLocation extends ReqBase{
    @ApiModelProperty("用户id")
    private Integer userId;
}
