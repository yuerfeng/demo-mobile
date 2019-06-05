package com.xyz.demo.service;

import com.xyz.demo.annotation.MobileTrans;
import com.xyz.demo.dao.UserDao;
import com.xyz.demo.exceptions.ServiceException;
import com.xyz.demo.pojo.Friend;
import com.xyz.demo.pojo.User;
import com.xyz.demo.model.Position;
import com.xyz.demo.model.UserPosition;
import com.xyz.demo.utils.IdGenUtils;
import com.xyz.demo.utils.JwtHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl extends CrudNormalService<User, UserDao> implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);




//    @Override
//    public User queryUser(String user, String pass) throws Exception {
//        for(Map.Entry<String,User> kv : users.entrySet()){
//            if(kv.getValue().getName().equals(user)){
//                if(kv.getValue().getPass().equals(pass)){
//                    return kv.getValue();
//                }else{
//                    throw new ServiceException("密码错误");
//                }
//            }
//        }
//
//        throw new ServiceException("用户不存在");
//    }

    @Override
    public User queryUserByMobile(String mobile) {
        User entity = new User();
        entity.setMobile(mobile);
        return getByEntity(entity);
    }

    @Override
    public List<Friend> queryUserFriends(String id) {
//        List<User> result = new LinkedList<>();
//        if(userFriends.get(id) != null){
//            List<String> ids = userFriends.get(id);
//            for(String i : ids){
//                result.add(users.get(i));
//            }
//            return result;
//        }
//        return result;
        return null;
    }

    @Override
    @MobileTrans
    public User login(String mobile, String password)  throws Exception{
        User user = this.queryUserByMobile(mobile);
        if(user == null){
            logger.error("该手机未注册");
            throw new ServiceException("该手机未注册") ;
        }else if(!StringUtils.equals(password,user.getPassword())){
            logger.error("密码错误");
            throw new ServiceException("密码错误") ;
        }

        String token = JwtHelper.createTokenById(Long.valueOf(user.getId()));
        user.setToken(token);
        user.setLoginStatus("1");
        this.save(user);

        return user;
    }

    @Override
    public User loginByVerifyCode(String mobile, String code)  throws Exception{
        User user = this.queryUserByMobile(mobile);
        if(user == null){
            logger.error("该手机未注册");
            throw new ServiceException("该手机未注册") ;
        }else if(!StringUtils.equals("111111",code)){
            logger.error("验证码错误");
            throw new ServiceException("验证码错误") ;
        }

        String token = IdGenUtils.uuid();
        user.setToken(token);
        user.setLoginStatus("1");
        this.save(user);

        return user;
    }

    @Override
    public Boolean logout(Integer id, String token) throws Exception {
        User user = this.get(id);
        if(user != null && StringUtils.equals(user.getToken(),token)){
            //正常退出
            user.setToken("");
            user.setLoginStatus("0");
            //写入
        }
        return Boolean.TRUE;
    }
}
