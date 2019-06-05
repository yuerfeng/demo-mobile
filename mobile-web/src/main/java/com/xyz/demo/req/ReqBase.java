package com.xyz.demo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class ReqBase implements Serializable {
    @ApiModelProperty("通讯token")
    private String token;
    private Integer pageSize;
    private Integer pageNo;

}
