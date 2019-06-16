package com.xyz.demo.service;

import com.xyz.demo.dao.UserDao;
import com.xyz.demo.pojo.Friend;
import com.xyz.demo.pojo.User;
import com.xyz.demo.model.Position;
import com.xyz.demo.model.UserPosition;

import java.util.List;

public interface UserService extends ICrudNormalService<User, UserDao> {
    User queryUserByMobile(String mobile);
    List<Friend> queryUserFriends(String id);
    User login(String mobile,String password)  throws Exception;
    User loginByVerifyCode(String mobile,String code)  throws Exception;
    Boolean logout(Integer id,String token) throws Exception;
    Boolean updateByMobile(String mobile,User user);
}
