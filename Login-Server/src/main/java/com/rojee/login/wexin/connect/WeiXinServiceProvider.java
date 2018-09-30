package com.rojee.login.wexin.connect;

import com.rojee.login.wexin.api.WeiXin;
import com.rojee.login.wexin.api.impl.WeiXinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class WeiXinServiceProvider extends AbstractOAuth2ServiceProvider<WeiXin> {

    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeiXinServiceProvider(String appid,String secret) {
        super(new WeiXinOAuth2Template(appid,secret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public WeiXin getApi(String s) {
        return new WeiXinImpl(s);
    }
}
