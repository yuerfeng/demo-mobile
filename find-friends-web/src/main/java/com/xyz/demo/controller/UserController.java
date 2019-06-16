package com.xyz.demo.controller;

import com.xyz.demo.enums.ErrorType;
import com.xyz.demo.enums.VerifyCodeTypeEnum;
import com.xyz.demo.exceptions.ServiceException;
import com.xyz.demo.pojo.User;
import com.xyz.demo.req.*;
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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Api("用户管理")
@RequestMapping("user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("registedCheck")
    @ApiOperation("检查是否已经注册,true表示已经注册,false表示未注册")
    public RtnMessage<Boolean> registTest(@RequestBody ReqRegistTest param) {
        if (StringUtils.isBlank(param.getMobile())) {
            return RtnMessageUtils.buildFailed("手机号必填");
        }
        //判断用户是否存在
        User temp = userService.queryUserByMobile(param.getMobile());
        return RtnMessageUtils.buildSuccess((temp != null));
    }

    @PostMapping("regist")
    @ApiOperation("用户注册接口")
    public RtnMessage<RtnUser> regist(@RequestBody ReqRegist param) {
        if (StringUtils.isBlank(param.getMobile()) || StringUtils.isBlank(param.getVerifyCode())) {
            return RtnMessageUtils.buildFailed("手机号和验证码必填");
        }
        if(!checkCode(VerifyCodeTypeEnum.REGIST,param.getMobile(),param.getVerifyCode())){
            return RtnMessageUtils.buildError(ErrorType.VERIFY_CODE_ERROR);
        }
        //判断用户是否存在
        User temp = new User();
        temp = userService.queryUserByMobile(param.getMobile());
        if (temp != null) {
            return RtnMessageUtils.buildFailed("该手机已经注册");
        } else {
            temp = new User();
        }

        BeanUtils.copyProperties(param, temp);
        if (userService.save(temp)) {
            RtnUser result = new RtnUser();
            temp = userService.queryUserByMobile(param.getMobile());
            BeanUtils.copyProperties(temp, result);
            return RtnMessageUtils.buildSuccess(result);
        } else {
            return RtnMessageUtils.buildFailed("注册失败");
        }
    }

    @PostMapping("update")
    @ApiOperation("更新用户资料")
    public RtnMessage<RtnUser> update(@RequestBody ReqRegist param) {
        if(!checkCode(VerifyCodeTypeEnum.REGIST,param.getMobile(),param.getVerifyCode())){
            return RtnMessageUtils.buildError(ErrorType.VERIFY_CODE_ERROR);
        }
        //判断用户是否存在
        User temp = new User();
        temp = userService.queryUserByMobile(param.getMobile());
        if (temp != null) {
            return RtnMessageUtils.buildFailed("该手机已经注册");
        } else {
            temp = new User();
        }

        BeanUtils.copyProperties(param, temp);
        if (userService.save(temp)) {
            RtnUser result = new RtnUser();
            temp = userService.queryUserByMobile(param.getMobile());
            BeanUtils.copyProperties(temp, result);
            return RtnMessageUtils.buildSuccess(result);
        } else {
            return RtnMessageUtils.buildFailed("注册失败");
        }
    }

    @PostMapping("login")
    @ApiOperation("密码登录")
    public RtnMessage<RtnLogin> login(@RequestBody ReqPassLogin param) {
        if (StringUtils.isBlank(param.getMobile()) || StringUtils.isBlank(param.getPassword())) {
            return RtnMessageUtils.buildFailed("手机号和密码必填");
        }
        try {
            User user = userService.login(param.getMobile(), param.getPassword());
            RtnLogin rtn = new RtnLogin();
            rtn.setToken(user.getToken());
            return RtnMessageUtils.buildSuccess(rtn);
        } catch (ServiceException e) {
            return RtnMessageUtils.buildFailed(e.getMessage());
        } catch (Exception e) {
            logger.error("login异常", e);
            return RtnMessageUtils.buildFailed("内部异常");
        }
    }

    @PostMapping("loginByMobile")
    @ApiOperation("验证码登录")
    public RtnMessage<RtnLogin> loginByMobile(@RequestBody ReqCodeLogin param) {
        if (StringUtils.isBlank(param.getMobile()) || StringUtils.isBlank(param.getVerifyCode())) {
            return RtnMessageUtils.buildFailed("手机号和验证码必填");
        }
        if(!checkCode(VerifyCodeTypeEnum.LOGIN,param.getMobile(),param.getVerifyCode())){
            return RtnMessageUtils.buildError(ErrorType.VERIFY_CODE_ERROR);
        }
        try {
            User user = userService.loginByVerifyCode(param.getMobile(), param.getVerifyCode());
            RtnLogin rtn = new RtnLogin();
            rtn.setToken(user.getToken());
            return RtnMessageUtils.buildSuccess(rtn);
        } catch (ServiceException e) {
            return RtnMessageUtils.buildFailed(e.getMessage());
        } catch (Exception e) {
            logger.error("login异常", e);
            return RtnMessageUtils.buildFailed("内部异常");
        }
    }

    @PostMapping("logout")
    @ApiOperation("退出登录")
    public RtnMessage<Boolean> logout(@RequestBody ReqLogout param) {
        if (StringUtils.isBlank(param.getToken()) || param.getId() == null) {
            return RtnMessageUtils.buildFailed("参数有误");
        }
        try {
            Boolean res = userService.logout(param.getId(), param.getToken());
            if (res) {
                return RtnMessageUtils.buildSuccess(res);
            } else {
                return RtnMessageUtils.buildFailed("退出失败");
            }
        } catch (ServiceException e) {
            return RtnMessageUtils.buildFailed(e.getMessage());
        } catch (Exception e) {
            logger.error("logout异常", e);
            return RtnMessageUtils.buildFailed("内部异常");
        }
    }

    @PostMapping("sendVerifyCode")
    @ApiOperation("发送验证码接口")
    public RtnMessage<Boolean> sendVerifyCode(@RequestBody ReqGetVerifyCode param) {
        if (StringUtils.isBlank(param.getMobile())) {
            return RtnMessageUtils.buildFailed("参数有误");
        }
        VerifyCodeTypeEnum type = VerifyCodeTypeEnum.getNameById(param.getType());
        if (type == null) {
            return RtnMessageUtils.buildFailed("参数有误");
        }
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        logger.info("给手机号" + param.getMobile() + "发送验证码：" + code);
        saveAndsendCode(type, param.getMobile(), code);
        return RtnMessageUtils.buildSuccess(true);
    }

    /**
     * 发送验证码
     * @param type
     * @param key
     * @param code
     */
    private void saveAndsendCode(VerifyCodeTypeEnum type, String key, String code) {
        saveCode(type,key,code);

        //发送短信
    }

    private void saveCode(VerifyCodeTypeEnum type, String key, String code) {
        String prekey = type.getName() + ":::" + key;
        stringRedisTemplate.opsForValue().set(prekey, code);
        stringRedisTemplate.expire(key, 5, TimeUnit.MINUTES);
    }

    /**
     * 验证验证码
     * @param type
     * @param key
     * @param code
     * @return
     */
    private boolean checkCode(VerifyCodeTypeEnum type, String key, String code) {
        String prekey = type.getName() + ":::" + key;
        String redisValue = stringRedisTemplate.opsForValue().get(prekey);
        boolean res =  StringUtils.equals(redisValue,code);
        if(res){
            //验证通过立即过期
            stringRedisTemplate.delete(prekey);
        }
        return res;
    }

    @PostMapping("checkVerifyCode")
    @ApiOperation("确认验证码接口")
    public RtnMessage<Boolean> checkVerifyCode(@RequestBody ReqCheckVerifyCode param) {
        if (StringUtils.isBlank(param.getMobile())) {
            return RtnMessageUtils.buildFailed("参数有误");
        }
        VerifyCodeTypeEnum type = VerifyCodeTypeEnum.getNameById(param.getType());
        if (type == null) {
            return RtnMessageUtils.buildFailed("参数有误");
        }
        if(!checkCode(type,param.getMobile(),param.getCode())){
            return RtnMessageUtils.buildError(ErrorType.VERIFY_CODE_ERROR);
        }
        return RtnMessageUtils.buildSuccess(true);
    }

    @PostMapping("checkAndGetCode")
    @ApiOperation("确认验证码并获取操作码")
    public RtnMessage<String> checkAndGetCode(@RequestBody ReqCheckVerifyCode param) {
        if (StringUtils.isBlank(param.getMobile())) {
            return RtnMessageUtils.buildFailed("参数有误");
        }
        VerifyCodeTypeEnum type = VerifyCodeTypeEnum.getNameById(param.getType());
        if (type == null) {
            return RtnMessageUtils.buildFailed("参数有误");
        }
        if(!checkCode(type,param.getMobile(),param.getCode())){
            return RtnMessageUtils.buildError(ErrorType.VERIFY_CODE_ERROR);
        }
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        logger.info("给手机号" + param.getMobile() + "操作码：" + code);
        saveCode(type,param.getMobile(),code);
        return RtnMessageUtils.buildSuccess(code);
    }


    @PostMapping("modifyPass")
    @ApiOperation("修改密码")
    public RtnMessage<Boolean> modifyPass(@RequestBody ReqModifyPass param) {
        if (StringUtils.isBlank(param.getCode()) || StringUtils.isBlank(param.getPassword())) {
            return RtnMessageUtils.buildError(ErrorType.PARA_ERROR);
        }
        if(!checkCode(VerifyCodeTypeEnum.MODIFY_PASS,param.getMobile(),param.getCode())){
            return RtnMessageUtils.buildError(ErrorType.OPERATOR_CODE_ERROR);
        }
        User user = new User();
        user.setMobile(param.getMobile());
        user = userService.getByEntity(user);
        user.setPassword(param.getPassword());
        return RtnMessageUtils.buildSuccess(userService.updateByMobile(param.getMobile(),user));
    }

}
