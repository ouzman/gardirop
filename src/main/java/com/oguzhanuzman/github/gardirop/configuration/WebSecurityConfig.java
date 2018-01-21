package com.oguzhanuzman.github.gardirop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final MemberUserDetailsService memberUserDetailsService;

    @Autowired
    public WebSecurityConfig(MemberUserDetailsService memberUserDetailsService) {
        this.memberUserDetailsService = memberUserDetailsService;
    }

    @Bean
    public AuthenticationEntryPoint basicAuthenticationEntryPoint() {
        BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new BasicAuthenticationEntryPoint();
        basicAuthenticationEntryPoint.setRealmName("gardirop");
        return basicAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()                               // index page
                .antMatchers(HttpMethod.POST, "/api/member").permitAll()    // register
                // TODO: .antMatchers(HttpMethod.GET, "/api/product").permitAll()       // product list
                .anyRequest().authenticated()
                .and()

                .httpBasic()
                .authenticationEntryPoint(basicAuthenticationEntryPoint())
                .and()

                .csrf()
                .disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(memberUserDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
