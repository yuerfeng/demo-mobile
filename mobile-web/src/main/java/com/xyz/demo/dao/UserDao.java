package com.xyz.demo.dao;
import com.xyz.demo.pojo.User;
public interface UserDao extends CrudDao<User> {
    Integer logout(User user);
}