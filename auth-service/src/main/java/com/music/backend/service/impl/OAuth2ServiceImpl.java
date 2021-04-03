package com.music.backend.service.impl;

import com.music.backend.base.userdetails.AuthProviderEnum;
import com.music.backend.base.userdetails.OAuth2Details;
import com.music.backend.base.userdetails.UserDetailsPrincipal;
import com.music.backend.exception.OAuth2AuthenticationProcessingException;
import com.music.backend.model.Account;
import com.music.backend.repository.AccountRepository;
import com.music.backend.util.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class OAuth2ServiceImpl extends DefaultOAuth2UserService {
    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        System.out.println("Processing o auth2 user");
        OAuth2Details oAuth2Details = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (ObjectUtils.isEmpty(oAuth2Details.getAttributes().get("email"))) {
            System.out.println("Get email:" + oAuth2Details.getAttributes().get("email"));
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        System.out.println("Email: " + oAuth2Details.getAttributes().get("email").toString());
        Account account = accountRepository.findFirstByEmail(oAuth2Details.getAttributes().get("email").toString());

        if (account != null) {
            System.out.println("Exist user:");
            System.out.print(account);
            if (!account.getProvider().equals(AuthProviderEnum.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                System.out.println("Looks like you're signed up ");
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        account.getProvider() + " account. Please use your " + account.getProvider() +
                        " account to login.");
            }
            account = updateExistingUser(account, oAuth2Details);
        } else {
            account = registerNewUser(oAuth2UserRequest, oAuth2Details);
        }

        return UserDetailsPrincipal.create(account, oAuth2User.getAttributes());
    }

    private Account registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2Details oAuth2UserInfo) {
        Account account = new Account();

        account.setProvider(AuthProviderEnum.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        account.setProviderId(oAuth2UserInfo.getId());
        account.setName(oAuth2UserInfo.getName());
        account.setEmail(oAuth2UserInfo.getEmail());
        return accountRepository.save(account);
    }

    private Account updateExistingUser(Account existingUser, OAuth2Details oAuth2UserInfo) {
        System.out.println("Updating existing user: " + oAuth2UserInfo.getEmail());
        existingUser.setName(oAuth2UserInfo.getName());
//        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return accountRepository.save(existingUser);
    }
}
