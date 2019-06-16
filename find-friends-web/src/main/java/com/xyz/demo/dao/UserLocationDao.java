package com.xyz.demo.dao;
import com.xyz.demo.pojo.UserLocation;
import org.apache.ibatis.annotations.Param;

public interface UserLocationDao extends CrudDao<UserLocation> {

    UserLocation findLatested(@Param("userId") Integer userId);
}