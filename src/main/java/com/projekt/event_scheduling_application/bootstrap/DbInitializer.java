package com.projekt.event_scheduling_application.bootstrap;


import com.projekt.event_scheduling_application.configuration.UserProperties;
import com.projekt.event_scheduling_application.dao.Role;
import com.projekt.event_scheduling_application.dao.User;
import com.projekt.event_scheduling_application.model.UserStatus;
import com.projekt.event_scheduling_application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Component
public class DbInitializer {

    @Value("${user.login:default@email.com}")
    private String email;


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserProperties userProperties;

    @EventListener(ContextRefreshedEvent.class)
    public void onStartup() {
        userRepository.save(new User("test@test.com",
                "Pass123",
                "Stefan",
                UserStatus.ACTIVATED,
                Role.REGULAR_USER, List.of()));
        /*
        userRepository.save(new User(userProperties.getLogin(),
                passwordEncoder.encode(userProperties.getPassword()),
                userProperties.getNick(),
                userProperties.getUserStatus(),
                userProperties.getRole(), List.of()));

         */
    }
}
