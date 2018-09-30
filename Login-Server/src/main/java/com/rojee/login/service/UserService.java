package com.rojee.login.service;

import com.rojee.core.entity.User;
import com.rojee.login.jpa.UserJpaRepository;
import com.rojee.core.redis.UIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;

    private final UIdGenerator idGenerator;

    @Autowired
    public UserService(UserJpaRepository userJpaRepository,UIdGenerator idGenerator) {
        this.userJpaRepository = userJpaRepository;
        this.idGenerator = idGenerator;
    }

    public User login(String username, String password){
        return userJpaRepository.findUserByAccountAndPassword(username,password);
    }

    public User register(String account,String password){
        boolean exists = userJpaRepository.existsUserByAccount(account);
        if (exists)
            return null;
        User user = new User();
        Long id = idGenerator.generate();
        if (id == null)
            return null;
        user.setId(id);
        user.setAccount(account);
        user.setPassword(password);
        return userJpaRepository.save(user);
    }
}
