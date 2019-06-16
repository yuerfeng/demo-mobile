package com.xyz.demo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("退出请求参数")
public class ReqLogout extends ReqBase{
    @ApiModelProperty("用户id")
    private Integer id;
}
