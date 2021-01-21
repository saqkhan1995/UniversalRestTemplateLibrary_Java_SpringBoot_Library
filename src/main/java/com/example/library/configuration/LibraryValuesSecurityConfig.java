package com.example.library.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties(prefix = "com.exmaple.library.security")
@Validated
//@Getter
//@Setter
public class LibraryValuesSecurityConfig {

    @NotBlank(message = "client Id must not be blank")
    private String clientId;

    @NotBlank(message = "client secret must not be blank")
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
