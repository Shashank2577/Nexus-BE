package com.example.emailnotification.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Map;

@RestController
@RequestMapping("/api/oauth2")
public class OAuth2Controller {
    
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }
    
    @GetMapping("/tokens/google")
    public OAuth2AuthorizedClient googleClient(
            @RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient client) {
        return client;
    }
    
    @GetMapping("/tokens/microsoft")
    public OAuth2AuthorizedClient microsoftClient(
            @RegisteredOAuth2AuthorizedClient("microsoft") OAuth2AuthorizedClient client) {
        return client;
    }
}
