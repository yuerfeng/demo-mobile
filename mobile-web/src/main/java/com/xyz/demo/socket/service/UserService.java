package com.xyz.demo.socket.service;

import com.xyz.demo.pojo.User;
import com.xyz.demo.socket.model.Position;
import com.xyz.demo.socket.model.UserPosition;

import java.util.List;

public interface UserService {
    /**
     * 查询用户
     * @param user
     * @param pass
     * @return
     */
    User queryUser(String user, String pass) throws Exception;
    User queryUser(String id);
    User queryUserWithFriends(String id);
    List<User> queryAllUser();

    /**
     * 查询用户的好友列表
     * @param id
     * @return
     */
    List<User> queryUserFriends(String id);

    List<UserPosition> queryFriendsPos(String id);

    void savePositon(User user, Position pos) throws Exception;
}
