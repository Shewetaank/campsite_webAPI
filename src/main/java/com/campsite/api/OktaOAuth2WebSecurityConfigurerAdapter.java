package com.campsite.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class OktaOAuth2WebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer().jwt();
        http.cors();
        http.authorizeRequests().antMatchers("/registerUser/create").permitAll().and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll().and()
        .authorizeRequests().antMatchers("/campsiteRegistration/anonymous/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}