package com.rojee.core.redis;

import com.rojee.core.entity.Room;
import com.rojee.core.entity.User;
import org.springframework.data.redis.core.RedisTemplate;



public class UserRedisService {

    private final RedisTemplate<Object,Object> redisTemplate;

    private final static String USER_REDIS_CACHE = "user_redis_cache";

    private final static String USER_ROOM = "user_room";

    public UserRedisService(RedisTemplate<Object,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(User user){
        redisTemplate.opsForHash().put(USER_REDIS_CACHE,user.getAccount(),user);
    }

    public User get(String account){
        return (User) redisTemplate.opsForHash().get(USER_REDIS_CACHE,account);
    }

    @SuppressWarnings("unchecked")
    public <T extends Room>T getUserRoom(User user){
        return (T) redisTemplate.opsForValue().get(USER_ROOM);
    }
}
