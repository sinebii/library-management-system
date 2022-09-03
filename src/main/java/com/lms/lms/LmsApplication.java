package com.lms.lms;

import com.lms.lms.security.AppProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class LmsApplication {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    public static void main(String[] args) {
        SpringApplication.run(LmsApplication.class, args);
    }
    @Bean
    public SpringApplicationContext springApplicationContext(){
        return new SpringApplicationContext();
    }

    @Bean("AppProperties")
    public AppProperties app(){
        return new AppProperties();
    }

}
