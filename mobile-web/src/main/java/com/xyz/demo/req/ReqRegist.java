package com.xyz.demo.req;

import lombok.Data;

@Data
public class ReqRegist {
    private String mobile;
    private String password;
    private String verifyCode;
}
