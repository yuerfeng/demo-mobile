package com.xyz.demo.pojo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
public class User extends DataEntity implements Serializable{
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
        private String loginStatus;
        private String token;

        private List<Friend> friendList = new LinkedList<>();
}