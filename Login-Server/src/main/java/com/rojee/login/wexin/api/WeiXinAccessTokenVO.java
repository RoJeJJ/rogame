package com.rojee.login.wexin.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeiXinAccessTokenVO {
    private String access_token;
    private long expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;
}
