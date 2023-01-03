package com.api.cossc.domain;

import java.util.HashMap;
import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
    public KakaoOAuth2UserInfo(Map<String, Object> attributes, String name) {
        super(attributes, name);
    }

    @Override
    public String getId() {
        return getKey();
    }

    @Override
    public String getName() {
        HashMap<String, String> properties = (HashMap<String, String>) attributes.get("properties");
        return properties.get("nickname");
    }

    @Override
    public String getEmail() {
        HashMap<String, String> kakaoAccount = (HashMap<String, String>) attributes.get("kakao_account");
        return kakaoAccount.get("email");
    }

    @Override
    public String getImageUrl() {
        HashMap<String, String> properties = (HashMap<String, String>) attributes.get("properties");
        return properties.get("profile_image");
    }
}
