package com.xyz.demo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("是否能注册接口参数")
public class ReqRegistTest {
    @ApiModelProperty("手机号")
    private String mobile;
}
