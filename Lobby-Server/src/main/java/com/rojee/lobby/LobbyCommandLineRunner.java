package com.rojee.lobby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class LobbyCommandLineRunner implements CommandLineRunner {

    private final RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    public LobbyCommandLineRunner(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
