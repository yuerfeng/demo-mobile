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
    @ApiModelProperty("用户id")
    private Integer id;
    @ApiModelProperty("生成时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("设备id")
    private String deviceToken;
    @ApiModelProperty("用户类型")
    private Integer type;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("年龄")
    private String age;
    @ApiModelProperty("生日")
    private Date birthday;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("国内国外")
    private String zhCn;
    @ApiModelProperty("消息推送token")
    private String pushToken;

    private List<Friend> friendList = new LinkedList<>();
}
