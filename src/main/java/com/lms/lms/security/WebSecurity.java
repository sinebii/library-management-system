package com.lms.lms.security;

import com.lms.lms.service.BaseUserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final BaseUserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(BaseUserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers(SecurityConstants.SIGNUP_URL)
                //.antMatchers(HttpMethod.POST,SecurityConstants.SIGNUP_URL)
                .permitAll()
                .antMatchers(SecurityConstants.BOOK_URL)
                .permitAll()
                .antMatchers(SecurityConstants.AUTHOR_URL)
                .permitAll()
                .antMatchers(SecurityConstants.PUBLISHER_URL)
                .permitAll()
                .antMatchers("/v2/api-docs","/swagger-ui/**","/swagger-resources/**","/swagger-ui/index.html#")
                .permitAll()
                .anyRequest().authenticated().and().addFilter(new AuthenticationFilter(authenticationManager()));
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
