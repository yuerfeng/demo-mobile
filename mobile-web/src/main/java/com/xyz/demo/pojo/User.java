package com.xyz.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("用户信息")
public class User implements Serializable,Cloneable {
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("密码")
    private String pass;
    @ApiModelProperty("用户id")
    private String id;
    @ApiModelProperty("用户的朋友")
    private List<User> friends;

    public User(String id,String name,String pass){
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
