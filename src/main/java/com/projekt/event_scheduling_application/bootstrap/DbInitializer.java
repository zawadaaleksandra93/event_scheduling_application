package com.projekt.event_scheduling_application.bootstrap;


import com.projekt.event_scheduling_application.configuration.EventProperties;
import com.projekt.event_scheduling_application.configuration.UserProperties;
import com.projekt.event_scheduling_application.dao.Event;
import com.projekt.event_scheduling_application.dao.Role;
import com.projekt.event_scheduling_application.dao.User;
import com.projekt.event_scheduling_application.model.UserStatus;
import com.projekt.event_scheduling_application.repositories.EventRepository;
import com.projekt.event_scheduling_application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

    private final EventRepository eventRepository;
    private final EventProperties eventProperties;


    @EventListener(ContextRefreshedEvent.class)
    public void onStartup() {
        User testUser = new User("test@test.com",
                passwordEncoder.encode("Pass123"),
                "Stefan",
                UserStatus.ACTIVATED,
                Role.REGULAR_USER,
                List.of(),
                List.of());
        User testUser2 = new User("test2@test.com",
                passwordEncoder.encode("Pass456"),
                "Tomasz",
                UserStatus.ACTIVATED,
                Role.ADMIN,
                List.of(),
                List.of());

        Event testEvent = new Event("test event", LocalDate.of(2020, 12, 14),
                LocalTime.of(9, 00), LocalTime.of(11, 00), "this is test event",
                testUser2, List.of());
        Event testEvent2 = new Event("second event", LocalDate.of(2021, 4, 2),
                LocalTime.of(14, 00), LocalTime.of(15, 30), "this is second event",
                testUser2, List.of());

        userRepository.save(testUser);
        userRepository.save(testUser2);

        eventRepository.save(testEvent);
        eventRepository.save(testEvent2);



/*
        userRepository.save(new User(userProperties.getLogin(),
                passwordEncoder.encode(userProperties.getPassword()),
                userProperties.getNick(),
                userProperties.getUserStatus(),
                userProperties.getRole(),
                userProperties.getListOfSignedOnEvents()
                ,List.of()));



        eventRepository.save(new Event(eventProperties.getName(),
                eventProperties.getEventDate(),
                eventProperties.getStartTime(),
                eventProperties.getEndTime(),
                eventProperties.getDescription(),
                eventProperties.getEventAdmin(),
                eventProperties.getListOfParticipants()));

 */


    }
}
