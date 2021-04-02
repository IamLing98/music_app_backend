package com.music.backend.util;

import com.music.backend.base.AuthProvider;
import com.music.backend.base.userdetails.OAuth2Details;
import com.music.backend.dto.FacebookOAuth2UserInfo;
import com.music.backend.dto.GithubOAuth2UserInfo;
import com.music.backend.dto.GoogleOAuth2UserInfo;
import com.music.backend.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2Details getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
