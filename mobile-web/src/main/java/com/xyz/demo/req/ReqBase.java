package com.xyz.demo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel("基础信息")
public class ReqBase implements Serializable {
    @ApiModelProperty("通讯token")
    private String token;

}
