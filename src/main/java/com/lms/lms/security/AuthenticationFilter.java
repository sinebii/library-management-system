package com.lms.lms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.lms.SpringApplicationContext;
import com.lms.lms.payload.request.UserLoginRequest;
import com.lms.lms.payload.response.CreateUserResponse;
import com.lms.lms.service.BaseUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException{
        try{
            UserLoginRequest credentials = new ObjectMapper()
                    .readValue(req.getInputStream(),UserLoginRequest.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword(),
                            new ArrayList<>())
            );
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,Authentication auth) throws IOException, ServletException{
        String username = ((User) auth.getPrincipal()).getUsername();
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.getTokenSecret())
                .compact();
        BaseUserService baseUserService = (BaseUserService) SpringApplicationContext.getBean("baseUserServiceImpl");
        CreateUserResponse createUserResponse= baseUserService.getUser(username);
        res.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+token);
        res.addHeader("UserID",createUserResponse.getUId());
        //res.addIntHeader("UserId", Math.toIntExact(createUserResponse.getId()));
    }
}
