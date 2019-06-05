package com.xyz.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("位置数据")
public class Position implements Serializable {
    @ApiModelProperty("坐标时间")
    private String time;
    @ApiModelProperty("纬度")
    private String latitude;
    @ApiModelProperty("经度")
    private String longitude;
}
