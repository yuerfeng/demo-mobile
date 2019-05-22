package com.xyz.demo.controller;

import com.xyz.demo.pojo.User;
import com.xyz.demo.socket.model.UserPosition;
import com.xyz.demo.socket.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api("信息查询")
public class IndexController {
    @Resource
    private UserService userService;

    @ApiOperation("所有用户查询")
    @GetMapping("queryAllUsers")
    public List<User> queryAllUsers(){
        return userService.queryAllUser();
    }

    @ApiOperation("用户和朋友信息")
    @GetMapping("queryUser")
    public User queryUser(@RequestParam("id") String id){
        return userService.queryUserWithFriends(id);
    }

    @ApiOperation("朋友和自己的位置信息")
    @GetMapping("queryPos")
    public List<UserPosition> queryPos(@RequestParam("id") String id){
        return userService.queryFriendsPos(id);
    }

    @GetMapping("doc")
    public void doc(HttpServletResponse response) throws Exception{
        response.sendRedirect("swagger-ui.html");
    }


}
