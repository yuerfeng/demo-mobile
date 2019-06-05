package com.xyz.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xyz.demo.pojo.UserLocation;
import com.xyz.demo.req.ReqQueryLocation;
import com.xyz.demo.rtn.RtnLocation;
import com.xyz.demo.rtn.RtnMessage;
import com.xyz.demo.service.UserLocationService;
import com.xyz.demo.service.UserService;
import com.xyz.demo.utils.RtnMessageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("location")
public class LocationController {

    private static Logger log = LoggerFactory.getLogger(LocationController.class);
    @Resource
    private UserLocationService locationService;
    @Resource
    private UserService userService;

    @ApiOperation("获取用户最新位置")
    @PostMapping("getLocation")
    public RtnMessage<RtnLocation> getUserLocation(@RequestBody ReqQueryLocation param) {

        UserLocation location = locationService.findLatested(param.getUserId());
        if(location != null){
            RtnLocation rtnLocation = new RtnLocation();
            BeanUtils.copyProperties(location,rtnLocation);
            return RtnMessageUtils.buildSuccess(rtnLocation);
        }else{
            return RtnMessageUtils.buildFailed("没有位置信息");
        }
    }

//    @RequestMapping("buy")
//    public Map<?, ?> buy(HttpServletRequest request, Long location_id, long user_id, String aid, int amount) {
//
//        if (location_id == null || location_id == 0) {
//            return ResultUtil.getResultMap(ERROR.ERR_PARAM);
//        }
//
//        if (amount < 0) {
//            return ResultUtil.getResultMap(ERROR.ERR_PARAM);
//        }
//        User user = userService.getUser(user_id);
//        if (user == null) {
//            return ResultUtil.getResultMap(ERROR.ERR_USER_NOT_EXIST);
//        }
//        if (TextUtils.isEmpty(aid)) {
//            return ResultUtil.getResultMap(ERROR.ERR_NOT_EXIST, "app not exist");
//        }
//        if (TextUtils.isEmpty(MODULE_PAY_URL)) {
//            Properties prop = PropertiesUtil.load("config.properties");
//            String value = PropertiesUtil.getProperty(prop, "MODULE_PAY_URL");
//            MODULE_PAY_URL = value;
//        }
//        String result = null;
//        try {
//            result = HttpsUtil.sendHttpsPost(MODULE_PAY_URL + "?user_id=" + user_id + "&aid=" + aid + "&int_amount="
//                    + amount + "&ext=" + location_id);
//        } catch (Exception e) {
//            log.error("购买失败" + e.getMessage());
//        }
//        if (!TextUtils.isEmpty(result)) {
//            Map<?, ?> map = JSONUtil.jsonToMap(result);
//            if (map.get("code").toString().equals("0")) {
//                UserLocationShip ship = new UserLocationShip();
//                ship.setUser_id(user_id);
//                ship.setLocation_id(location_id);
//                ship.setStatus(UserLocationShipType.BUYED.ordinal());
//                locationService.insertShip(ship);
//            }
//            return map;
//        }
//        return ResultUtil.getResultMap(ERROR.ERR_SYS);
//    }

//    @RequestMapping("set_location_status")
//    public Map<?, ?> setLocationStatus(HttpServletRequest request, Long location_id, long user_id, String aid) {
//        if (location_id == null || location_id == 0) {
//            return ResultUtil.getResultMap(ERROR.ERR_PARAM);
//        }
//        User user = userService.getUser(user_id);
//        if (user == null) {
//            return ResultUtil.getResultMap(ERROR.ERR_USER_NOT_EXIST);
//        }
//        if (TextUtils.isEmpty(aid)) {
//            return ResultUtil.getResultMap(ERROR.ERR_NOT_EXIST, "app not exist");
//        }
//        UserLocationShip ship = new UserLocationShip();
//        ship.setUser_id(user_id);
//        ship.setLocation_id(location_id);
//        ship.setStatus(UserLocationShipType.BUYED.ordinal());
//        locationService.insertShip(ship);
//        return ResultUtil.getResultOKMap().addAttribute("location_id", location_id);
//    }
}
