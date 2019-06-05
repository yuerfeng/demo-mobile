package com.xyz.demo.service;

import com.xyz.demo.dao.UserLocationDao;
import com.xyz.demo.pojo.UserLocation;

public interface UserLocationService extends ICrudNormalService<UserLocation, UserLocationDao> {

    UserLocation findLatested(Integer userId);
}
