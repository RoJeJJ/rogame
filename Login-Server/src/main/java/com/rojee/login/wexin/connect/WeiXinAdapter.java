package com.rojee.login.wexin.connect;

import com.rojee.login.wexin.api.WeiXin;
import com.rojee.login.wexin.api.WeixinUserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class WeiXinAdapter implements ApiAdapter<WeiXin> {

    @Setter
    @Getter
    private String openid;

    public WeiXinAdapter(){}

    public WeiXinAdapter(String openid){
        this.openid = openid;
    }

    @Override
    public boolean test(WeiXin weiXin) {
        return true;
    }

    @Override
    public void setConnectionValues(WeiXin weiXin, ConnectionValues connectionValues) {
        WeixinUserInfo userInfo = weiXin.getUserInfo(openid);
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setProviderUserId(userInfo.getOpenid());
        connectionValues.setImageUrl(userInfo.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(WeiXin weiXin) {
        return null;
    }

    @Override
    public void updateStatus(WeiXin weiXin, String s) {

    }
}
