package com.rojee.login.wexin.connect;

import com.rojee.login.wexin.api.WeiXin;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;

/**
 * 微信连接工厂
 */
public class WeixinConnectionFactory extends OAuth2ConnectionFactory<WeiXin> {
    public WeixinConnectionFactory(String providerId, String appid, String secret) {
        super(providerId, new WeiXinServiceProvider(appid,secret), new WeiXinAdapter());
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if (accessGrant instanceof WeiXinAccessGrant)
            return ((WeiXinAccessGrant) accessGrant).getOpenid();
        return super.extractProviderUserId(accessGrant);
    }
}
