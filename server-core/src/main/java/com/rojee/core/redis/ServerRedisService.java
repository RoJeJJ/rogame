package com.rojee.core.redis;

import com.rojee.core.server.ServerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class ServerRedisService {

    private static final String SERVER_REDIS_CACHE = "server_redis_cache";

    private final RedisTemplate<Object,Object> redisTemplate;

    public ServerRedisService(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(ServerInfo info){
        boolean b = redisTemplate.opsForHash().putIfAbsent(SERVER_REDIS_CACHE,info.getId(),info);
        if (!b)
            log.warn("服务器id:{}注册失败",info.getId());
    }

    public ServerInfo getServerInfo(long id){
        return (ServerInfo) redisTemplate.opsForHash().get(SERVER_REDIS_CACHE,id);
    }
}
