package com.xyz.demo.service;

import com.xyz.demo.dao.UserLocationDao;
import com.xyz.demo.pojo.UserLocation;
import org.springframework.stereotype.Service;

@Service
public class UserLocationServiceImpl extends CrudNormalService<UserLocation, UserLocationDao> implements UserLocationService {
    @Override
    public UserLocation findLatested(Integer userId){
        return dao.findLatested(userId);
    }
}
