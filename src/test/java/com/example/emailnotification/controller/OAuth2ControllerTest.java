package com.example.emailnotification.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OAuth2Controller.class)
class OAuth2ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OAuth2AuthorizedClientService clientService;

    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
    }

    @Test
    void googleLogin_ShouldRedirectToGoogleAuth() throws Exception {
        mockMvc.perform(get("/oauth2/gmail/login").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("https://accounts.google.com/o/oauth2/auth*"))
                .andExpect(redirectedUrlPattern("*state=" + session.getId() + "*"));
    }

    @Test
    void microsoftLogin_ShouldRedirectToMicrosoftAuth() throws Exception {
        mockMvc.perform(get("/oauth2/microsoft/login").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("https://login.microsoftonline.com/*/oauth2/v2.0/authorize*"))
                .andExpect(redirectedUrlPattern("*state=" + session.getId() + "*"));
    }

    @Test
    void callback_WithValidStateAndCode_ShouldReturnSuccess() throws Exception {
        String validState = session.getId();
        String validCode = "valid_code";

        mockMvc.perform(get("/oauth2/callback")
                .session(session)
                .param("code", validCode)
                .param("state", validState))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Authentication successful"));
    }

    @Test
    void callback_WithInvalidState_ShouldReturnUnauthorized() throws Exception {
        String invalidState = "invalid_state";
        String validCode = "valid_code";

        mockMvc.perform(get("/oauth2/callback")
                .session(session)
                .param("code", validCode)
                .param("state", invalidState))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("authentication_error"));
    }
}
