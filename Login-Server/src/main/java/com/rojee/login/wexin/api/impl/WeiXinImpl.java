package com.rojee.login.wexin.api.impl;

import com.rojee.login.wexin.api.WeiXin;
import com.rojee.login.wexin.api.WeixinUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

@Slf4j
public class WeiXinImpl extends AbstractOAuth2ApiBinding implements WeiXin {

    private static final String WEIXIN_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";

    private String accessToken;

    public WeiXinImpl(String accessToken){
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.accessToken = accessToken;
    }

    @Override
    public WeixinUserInfo getUserInfo(String openid) {
        String url = String.format(WEIXIN_USER_INFO_URL,accessToken,openid);
        WeixinUserInfo userInfo = getRestTemplate().getForObject(url,WeixinUserInfo.class);
        if (userInfo == null)
            log.error("getUserInfo:null");
        return userInfo;
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     *
     * @return the message converters
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters =  super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }
}
