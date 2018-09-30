package com.rojee.login.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "social.wechat")
public class WeiXinProperties {
    private String providerId;
    private String appid;
    private String secret;
}
