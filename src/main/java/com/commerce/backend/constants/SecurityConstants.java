package com.commerce.backend.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class SecurityConstants {

    @Value("${security.public-pattern}")
    private String publicPattern;

    @Value("${security.auth.username}")
    private String authUsername;

    @Value("${security.auth.auth-url}")
    private String authUrl;

    @Value("${security.auth.client_id}")
    private String clientId;

    @Value("${security.auth.client_password}")
    private String clientPassword;

    @Value("${security.auth.connection_timeout}")
    private String connectionTimeout;

    @Value("${security.auth.read_timeout}")
    private String readTimeout;

    @Value("${security.auth.public-url}")
    private String[] publicUrl;
    
}