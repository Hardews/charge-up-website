package com.hardews.jizhang.controller;

import com.hardews.jizhang.dto.LoginVo;
import com.hardews.jizhang.dto.LoginVoByEmail;
import com.hardews.jizhang.dto.UserVo;
import com.hardews.jizhang.utils.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hardews.jizhang.entity.UserEntity;
import com.hardews.jizhang.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("jizhang/user")
@Api("用户相关")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/info/{id}")

    public R info(@PathVariable("id") Long id){
		UserEntity user = userService.getById(id);
        return R.ok().put("user", user);
    }

    @ApiOperation("注册用户")
    @PostMapping("/register")

    public R save(@RequestBody @Valid UserVo user){
		return userService.register(user);
    }

    @RequestMapping("/update")
    public R update(@RequestBody UserVo user){
		return userService.updateUser(user);
    }

    @RequestMapping("/login")
    @ApiOperation("使用密码登录")
    public R login(@RequestBody LoginVo loginVo){
        return  userService.login(loginVo);
    }

    @RequestMapping("/login/email")
    @ApiOperation("使用邮箱验证登录")
    public R loginByEmail(@RequestBody LoginVoByEmail loginVoByEmail){
        return userService.loginByEmail(loginVoByEmail);
    }

    @ApiOperation("发送验证码")
    @GetMapping("/sendCode")
    public R sendCode(@RequestParam String email){
        return userService.sendCode(email);
    }

    @GetMapping("/logout")
    public R logout(){
        userService.logout();
        return R.ok("退出成功");
    }

}