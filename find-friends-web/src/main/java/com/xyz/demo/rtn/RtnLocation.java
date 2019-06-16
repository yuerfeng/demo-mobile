package com.xyz.demo.rtn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
@ApiModel("位置信息")
public class RtnLocation {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("位置经度")
    private String lat;
    @ApiModelProperty("位置纬度")
    private String lng;
    @ApiModelProperty("生成时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
