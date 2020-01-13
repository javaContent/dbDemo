package com.example.one.controller;

import com.example.one.entity.Result;
import com.example.one.entity.SysUser;
import com.example.one.service.UserServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

@Api(value = "用户登录管理")
@RestController
public class AuthController {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);
	
    @Autowired
    private UserServiceI userService;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public Result<?> createAuthenticationToken(String username,String password) 
    		throws AuthenticationException {
        logger.info("登陆：" +username);
        SysUser user = userService.login(username,password);
        return Result.success(user);
    }
 
    @ApiOperation(value = "注册")
    @RequestMapping(value = "auth/register", method = RequestMethod.POST)
    public Result<SysUser> register(@RequestBody SysUser addedUser) throws AuthenticationException{
        logger.info("注册：" +addedUser.getUserName());
        return Result.success(userService.register(addedUser));
    }
    
    @ApiOperation(value = "是否登录")
    @RequestMapping(value = "auth/isLogin", method = RequestMethod.POST)
    public Boolean isLogin() throws AuthenticationException{
        return true;
    }

    @ApiOperation(value = "获取用户名")
    @RequestMapping(value="/auth/getUserName", method = RequestMethod.GET)
    @ResponseBody
    public String login() {
        SysUser user = userService.selectById(1);
        return user.getUserName();
    }



}
