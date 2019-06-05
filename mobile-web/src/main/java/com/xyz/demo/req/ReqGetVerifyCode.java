package com.xyz.demo.req;

import lombok.Data;

@Data
public class ReqGetVerifyCode extends ReqBase{
    private String mobile;
}
