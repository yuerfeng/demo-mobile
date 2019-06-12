package com.xyz.demo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改密码参数")
public class ReqModifyPass {
    @ApiModelProperty("操作码")
    private String code;
    @ApiModelProperty("新密码")
    private String password;
    @ApiModelProperty("手机号")
    private String mobile;
}
