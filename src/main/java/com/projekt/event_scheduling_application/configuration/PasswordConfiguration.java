package com.projekt.event_scheduling_application.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
     //   return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
}
