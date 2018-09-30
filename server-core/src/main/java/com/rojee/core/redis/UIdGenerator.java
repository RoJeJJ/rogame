package com.rojee.core.redis;

import com.rojee.core.server.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Random;

public final class UIdGenerator {

    private final static String ID_USER_COUNTER = "id.user.counter";


    private final StringRedisTemplate redisTemplate;

    @Autowired
    public UIdGenerator(StringRedisTemplate redisTemplate,ServerInfo serverInfo) {
        this.redisTemplate = redisTemplate;
        if (this.redisTemplate.opsForValue().get(ID_USER_COUNTER) == null){
            this.redisTemplate.opsForValue().set(ID_USER_COUNTER,String.valueOf(serverInfo.getId()));
        }
    }

    public Long generate(){
        int delta = new Random().nextInt(4)+1;
        Long id = this.redisTemplate.opsForValue().increment(ID_USER_COUNTER,delta);
        if (id == null)
            return null;
        return id;
    }
}
