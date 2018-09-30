package com.rojee.login.wexin.connect;

import com.rojee.login.wexin.api.WeiXinAccessTokenVO;
import lombok.Getter;
import org.springframework.social.oauth2.AccessGrant;

public class WeiXinAccessGrant extends AccessGrant {

    @Getter
    private String openid;

    @Getter
    private String unionid;

    public WeiXinAccessGrant(WeiXinAccessTokenVO accessTokenVO) {
        super(accessTokenVO.getAccess_token(),accessTokenVO.getScope(),accessTokenVO.getRefresh_token(),accessTokenVO.getExpires_in());
        this.openid = accessTokenVO.getOpenid();
        this.unionid = accessTokenVO.getUnionid();
    }
}
