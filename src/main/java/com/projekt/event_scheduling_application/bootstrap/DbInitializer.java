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
        Data data= new Data();
        User testUser = new User(data.USER_NAME1,
                passwordEncoder.encode(data.USER_PASS1),
                data.USER_NICK1,
                UserStatus.ACTIVATED,
                Role.REGULAR_USER,
                List.of(),
                List.of());
        User testUser2 = new User(data.USER_NAME2,
                passwordEncoder.encode(data.USER_PASS2),
                data.USER_NICK2,
                UserStatus.ACTIVATED,
                Role.ADMIN,
                List.of(),
                List.of());

        Event testEvent = new Event(data.EVENT_NAME1,
                data.EVENT_DAY1,
                data.EVENT_START1,
                data.EVENT_END1,
                data.EVENT_DESCRIPTION1,
                testUser2, List.of());
        Event testEvent2 = new Event(data.EVENT_NAME2,
                data.EVENT_DAY2,
                data.EVENT_START2,
                data.EVENT_END2,
                data.EVENT_DESCRIPTION2,
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
