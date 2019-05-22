package com.xyz.demo.socket.service;

import com.xyz.demo.exceptions.ServiceException;
import com.xyz.demo.pojo.User;
import com.xyz.demo.socket.model.Position;
import com.xyz.demo.socket.model.UserPosition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {
    static Map<String, User> users = new ConcurrentHashMap<>(3);
    static Map<String, Position> userPos = new HashMap<>();
    static Map<String, List<String>> userFriends = new ConcurrentHashMap<>(10);

    static
    {
        List<String> ids = new LinkedList<>();
        for (int i = 1; i <= 3; i++) {
            String id = i+ "";
            String name = "user" + i;
            users.put(id, new User(id, name, "111111"));
            Queue<Position> queue = new LinkedList<>();
            userPos.put(id, null);
            ids.add(i+"");
        }


        for(String s : ids){
            userFriends.put(s,ids);
        }

    }

    @Override
    public User queryUser(String user, String pass) throws Exception {
        for(Map.Entry<String,User> kv : users.entrySet()){
            if(kv.getValue().getName().equals(user)){
                if(kv.getValue().getPass().equals(pass)){
                    return kv.getValue();
                }else{
                    throw new ServiceException("密码错误");
                }
            }
        }

        throw new ServiceException("用户不存在");
    }

    @Override
    public User queryUser(String id){
        return users.get(id);
    }

    @Override
    public User queryUserWithFriends(String id){
        try{
            User user = (User) users.get(id).clone();
            if(user == null){
                return null;
            }

            List<String> friendsIds = userFriends.get(id);
            if(CollectionUtils.isEmpty(friendsIds)){
                return user;
            }
            for(String s : friendsIds){
                if(user.getFriends() == null){
                    user.setFriends(new LinkedList<>());
                }
                try{
                    User temp = (User)users.get(s).clone();
                    user.getFriends().add((temp) );
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<User> queryAllUser(){
        List<User> result = new LinkedList<>();
        for(Map.Entry<String,User> kv : users.entrySet()){
            result.add(kv.getValue());
        }
        return result;
    }

    @Override
    public List<User> queryUserFriends(String id) {
        List<User> result = new LinkedList<>();
        if(userFriends.get(id) != null){
            List<String> ids = userFriends.get(id);
            for(String i : ids){
                result.add(users.get(i));
            }
            return result;
        }
        return result;
    }

    @Override
    public List<UserPosition> queryFriendsPos(String id) {
        List<UserPosition> result = new LinkedList<>();
        List<User> users = queryUserFriends(id);
        if(CollectionUtils.isEmpty(users)){
            return result;
        }

        for(User user : users){
            UserPosition userPosition = new UserPosition();
            BeanUtils.copyProperties(user,userPosition);
            userPosition.setPosition(new Position());
            if(userPos.get(user.getId()) != null){
                BeanUtils.copyProperties(userPos.get(user.getId()),userPosition.getPosition());
                result.add(userPosition);
            }
        }
        return result;
    }

    @Override
    public void savePositon(User user, Position pos) throws Exception{
        if(user == null || pos == null || StringUtils.isBlank(user.getId()) || StringUtils.isBlank(pos.getLatitude())
        || StringUtils.isBlank(pos.getLongitude())){
            throw new ServiceException("参数有误");
        }
        if(users.get(user.getId()) != null){
            userPos.put(user.getId(),pos);
        }
    }
}
