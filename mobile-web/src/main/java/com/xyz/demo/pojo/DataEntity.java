package com.xyz.demo.pojo;

import lombok.Data;

import java.util.Date;

@Data
public abstract class DataEntity {
    private Integer id;
    private String delFlag;
    private Date createTime;
    private Date updateTime;
}
