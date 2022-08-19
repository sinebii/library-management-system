package com.lms.lms.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UtilsHelper {
    private final Random random = new Random();
    //private final String alpha ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnnopqrstuvwxyz";
    private final String alpha ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnnopqrstuvwxyz";

    public String generateRandom(int len){
        return generateRandomString(len);
    }

    private String generateRandomString(int len){
        StringBuilder randomValue = new StringBuilder(len);
        for(int i=0;i<len;i++){
            randomValue.append(alpha.charAt(random.nextInt(alpha.length())));
        }
        return new String(randomValue);
    }
}
