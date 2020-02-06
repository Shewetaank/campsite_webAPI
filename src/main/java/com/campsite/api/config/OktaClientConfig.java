package com.campsite.api.config;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OktaClientConfig {

    @Bean
    public Client client() {
        return Clients.builder()
                .setOrgUrl("https://dev-356601.okta.com")
                .setClientCredentials(new TokenClientCredentials("00Rvaj0ndczSTg6dDTcDO2S1iD2Up5h7YqqClbNe_I"))
                .build();
    }
}
