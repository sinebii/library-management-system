package com.lms.lms.security;

import com.lms.lms.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGNUP_URL = "/users/**";
    public static final String BOOK_URL = "/api/v1/book/**";
    public static final String AUTHOR_URL = "/api/v1/author/**";
    public static final String PUBLISHER_URL = "/api/v1/publisher/**";

    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
    }


}
