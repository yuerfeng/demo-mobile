package com.xyz.demo.rtn;

import lombok.Data;

import java.util.Date;
@Data
public class RtnLocation {
    private Integer userId;
    private String lat;
    private String lng;
    private Date createTime;
    private Date updateTime;
}
