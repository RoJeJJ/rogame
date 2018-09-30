package com.rojee.login.controller;

import com.rojee.core.entity.User;
import com.rojee.core.redis.UserRedisService;
import com.rojee.login.response.ServerResponse;
import com.rojee.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.UUID;

@Slf4j
@RestController
public class LoginController {

    private final UserRedisService userRedisService;

    private final UserService userService;

    private final static int passwordLen = 6;

    @Autowired
    public LoginController(UserRedisService userRedisService, UserService userService) {
        this.userRedisService = userRedisService;
        this.userService = userService;
    }

    @PostMapping("/login/common")
    public String login(@RequestParam("username")String username,@RequestParam("password")String password){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            log.info("用户或密码不能为空");
            return ServerResponse.buildResponse(ServerResponse.EmptyAccountOrPassword);
        }
        User user = userRedisService.get(username);
        if (user == null){
            user = userService.login(username,password);
            if (user == null){
                log.info("用户名或密码错误");
                return ServerResponse.buildResponse(ServerResponse.InvalidAccountOrPassword);
            }
            userRedisService.save(user);
        }else {
            if (!password.equals(user.getPassword())){
                log.info("用户名或密码错误");
                return ServerResponse.buildResponse(ServerResponse.InvalidAccountOrPassword);
            }
            log.info("用户存在redis缓存中");
        }
        String token = UUID.randomUUID().toString().replace("-","");
        user.setGameToken(token);
        return user.gson();
    }

    @PostMapping("/register")
    @Transactional
    public String register(@RequestParam("username")String username,@RequestParam("password")String password){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            log.info("用户名或密码不能为空");
            return ServerResponse.buildResponse(ServerResponse.EmptyAccountOrPassword);
        }
        if (password.length() < passwordLen){
            log.info("密码长度不够");
            return ServerResponse.buildResponse(ServerResponse.PasswordLengthNotEnough);
        }
        User user = userService.register(username,password);
        if (user == null){
            return ServerResponse.buildResponse(ServerResponse.AccountAlreadyExists);
        }
        return ServerResponse.buildResponse(ServerResponse.Success);
    }
}
