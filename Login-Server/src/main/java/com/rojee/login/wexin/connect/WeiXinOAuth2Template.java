package com.rojee.login.wexin.connect;

import com.rojee.login.wexin.api.WeiXinAccessTokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Slf4j
public class WeiXinOAuth2Template extends OAuth2Template {

    private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&refresh_token=%s&grant_type=refresh_token";

    private String clientId;

    private String clientSecret;

    private String accessTokenUrl;

    public WeiXinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {

        String accessTokenRequestUrl = accessTokenUrl + "?appid=" + clientId +
                "&secret=" + clientSecret +
                "&code=" + authorizationCode +
                "&grant_type=authorization_code" +
                "&redirect_uri=" + redirectUri;
        return getAccessToken(accessTokenRequestUrl);
    }

    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        String url = String.format(REFRESH_TOKEN_URL,clientId,refreshToken);
        return getAccessToken(url);
    }

    private AccessGrant getAccessToken(String accessTokenRequestUrl) {
        WeiXinAccessTokenVO weiXinAccessTokenVO = getRestTemplate().getForObject(accessTokenRequestUrl,WeiXinAccessTokenVO.class);
        if (weiXinAccessTokenVO == null){
            log.warn("获取微信access_token失败");
            return null;
        }
        return new WeiXinAccessGrant(weiXinAccessTokenVO);
    }

    /**
     * 微信返回的contentType是html/text，添加相应的HttpMessageConverter来处理。
     *
     * @return the rest template
     */
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate =  super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
