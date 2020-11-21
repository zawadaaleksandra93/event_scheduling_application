package com.projekt.event_scheduling_application.configuration;


import com.projekt.event_scheduling_application.domain.Event;
import com.projekt.event_scheduling_application.domain.Role;
import com.projekt.event_scheduling_application.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "user")
@Component
public class UserProperties {
    private String login;
    private String password;
    private String nick;
    private UserStatus userStatus;
    private Role role;
    private List<Event> listOfSignedOnEvents;
}
