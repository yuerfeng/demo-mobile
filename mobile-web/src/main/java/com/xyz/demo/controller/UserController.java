package com.xyz.demo.controller;

import com.xyz.demo.exceptions.ServiceException;
import com.xyz.demo.pojo.User;
import com.xyz.demo.req.ReqGetVerifyCode;
import com.xyz.demo.req.ReqLogout;
import com.xyz.demo.req.ReqLogin;
import com.xyz.demo.req.ReqRegist;
import com.xyz.demo.rtn.RtnLogin;
import com.xyz.demo.rtn.RtnMessage;
import com.xyz.demo.rtn.RtnUser;
import com.xyz.demo.service.UserService;
import com.xyz.demo.utils.RtnMessageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api("用户管理")
@RequestMapping("user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @PostMapping("regist")
    @ApiOperation("用户注册接口")
    public RtnMessage<RtnUser> regist(@RequestBody ReqRegist user) {
        if(StringUtils.isBlank(user.getMobile()) || StringUtils.isBlank(user.getVerifyCode())){
            return RtnMessageUtils.buildFailed("手机号和验证码必填");
        }
        if(!"111111".equals(user.getVerifyCode())){
            return RtnMessageUtils.buildFailed("验证码错误");
        }
        //判断用户是否存在
        User temp = new User();
        temp = userService.queryUserByMobile(user.getMobile());
        if(temp != null){
            return RtnMessageUtils.buildFailed("该手机已经注册");
        }else{
            temp = new User();
        }

        BeanUtils.copyProperties(user,temp);
        if(userService.save(temp)){
            RtnUser result = new RtnUser();
            temp = userService.queryUserByMobile(user.getMobile());
            BeanUtils.copyProperties(temp,result);
            return RtnMessageUtils.buildSuccess(result);
        }else{
            return RtnMessageUtils.buildFailed("注册失败");
        }
    }

    @PostMapping("login")
    @ApiOperation("密码登录")
    public RtnMessage<RtnLogin> login(@RequestBody ReqLogin param) {
        if(StringUtils.isBlank(param.getMobile()) || StringUtils.isBlank(param.getPassword())){
            return RtnMessageUtils.buildFailed("手机号和密码必填");
        }
        try{
            User user = userService.login(param.getMobile(), param.getPassword());
            RtnLogin rtn = new RtnLogin();
            rtn.setToken(user.getToken());
            return RtnMessageUtils.buildSuccess(rtn);
        }catch (ServiceException e){
            return RtnMessageUtils.buildFailed(e.getMessage());
        }catch (Exception e){
            logger.error("login异常",e);
            return RtnMessageUtils.buildFailed("内部异常");
        }
    }

    @PostMapping("loginByMobile")
    @ApiOperation("验证码登录")
    public RtnMessage<RtnLogin> loginByMobile(@RequestBody ReqLogin param) {
        if(StringUtils.isBlank(param.getMobile()) || StringUtils.isBlank(param.getVerifyCode())){
            return RtnMessageUtils.buildFailed("手机号和验证码必填");
        }
        try{
            User user = userService.loginByVerifyCode(param.getMobile(), param.getVerifyCode());
            RtnLogin rtn = new RtnLogin();
            rtn.setToken(user.getToken());
            return RtnMessageUtils.buildSuccess(rtn);
        }catch (ServiceException e){
            return RtnMessageUtils.buildFailed(e.getMessage());
        }catch (Exception e){
            logger.error("login异常",e);
            return RtnMessageUtils.buildFailed("内部异常");
        }
    }

    @PostMapping("logout")
    @ApiOperation("退出登录")
    public RtnMessage<Boolean> logout(@RequestBody ReqLogout param) {
        if(StringUtils.isBlank(param.getToken()) || param.getId() == null){
            return RtnMessageUtils.buildFailed("参数有误");
        }
        try{
            Boolean res = userService.logout(param.getId(),param.getToken());
            if(res){
                return RtnMessageUtils.buildSuccess(res);
            }else{
                return RtnMessageUtils.buildFailed("退出失败");
            }
        }catch (ServiceException e){
            return RtnMessageUtils.buildFailed(e.getMessage());
        }catch (Exception e){
            logger.error("logout异常",e);
            return RtnMessageUtils.buildFailed("内部异常");
        }
    }

    @PostMapping("sendVerifyCode")
    @ApiOperation("发送验证码接口")
    public RtnMessage<Boolean> sendVerifyCode(@RequestBody ReqGetVerifyCode param) {
        if(StringUtils.isBlank(param.getMobile())){
            return RtnMessageUtils.buildFailed("参数有误");
        }
        logger.info("给手机号" + param.getMobile() + "发送验证码：123456");
        return RtnMessageUtils.buildSuccess(true);
    }

}
