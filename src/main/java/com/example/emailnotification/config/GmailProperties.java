package com.example.emailnotification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gmail")
public class GmailProperties {
    private Client client;
    private OAuth2 oauth2;
    private Project project;

    // Getters and setters
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public OAuth2 getOauth2() { return oauth2; }
    public void setOauth2(OAuth2 oauth2) { this.oauth2 = oauth2; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public static class Client {
        private String id;
        private String secret;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getSecret() { return secret; }
        public void setSecret(String secret) { this.secret = secret; }
    }

    public static class OAuth2 {
        private String authUri;
        private String tokenUri;
        private String redirectUri;
        private String providerCertUrl;

        public String getAuthUri() { return authUri; }
        public void setAuthUri(String authUri) { this.authUri = authUri; }
        public String getTokenUri() { return tokenUri; }
        public void setTokenUri(String tokenUri) { this.tokenUri = tokenUri; }
        public String getRedirectUri() { return redirectUri; }
        public void setRedirectUri(String redirectUri) { this.redirectUri = redirectUri; }
        public String getProviderCertUrl() { return providerCertUrl; }
        public void setProviderCertUrl(String providerCertUrl) { this.providerCertUrl = providerCertUrl; }
    }

    public static class Project {
        private String id;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
    }
}
