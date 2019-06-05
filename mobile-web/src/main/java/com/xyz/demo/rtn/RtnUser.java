package com.xyz.demo.rtn;

import com.xyz.demo.pojo.Friend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Data
@ApiModel("用户信息")
public class RtnUser {
    private Integer id;
    private String createTime;
    private String updateTime;
    private String mobile;
    private String password;
    private String deviceToken;
    private Integer type;
    private String name;
    private String nickName;
    private String age;
    private Date birthday;
    private String sex;
    private String avatar;
    private String email;
    private String zhCn;
    private String pushToken;

    private List<Friend> friendList = new LinkedList<>();
}
