package com.rojee.login;

import com.rojee.core.redis.ServerRedisService;
import com.rojee.core.redis.UIdGenerator;
import com.rojee.core.redis.UserRedisService;
import com.rojee.core.server.ServerInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


@SpringBootApplication
@EnableEurekaClient
@EntityScan(basePackages = "com.rojee.core.entity")
public class LoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class);
    }

    @Bean
    public UserRedisService userRedisService(RedisTemplate<Object,Object> redisTemplate){
        return new UserRedisService(redisTemplate);
    }

    @Bean
    public ServerRedisService serverRedisService(RedisTemplate<Object,Object> redisTemplate){
        return new ServerRedisService(redisTemplate);
    }

    @Bean
    public UIdGenerator uIdGenerator(StringRedisTemplate stringRedisTemplate, ServerInfo serverInfo){
        return new UIdGenerator(stringRedisTemplate,serverInfo);
    }

    @Bean
    @ConfigurationProperties(prefix = "server-info")
    public ServerInfo serverInfo(){
        return new ServerInfo();
    }
}
