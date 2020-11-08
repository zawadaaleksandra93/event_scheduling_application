package com.projekt.event_scheduling_application.configuration;


import com.projekt.event_scheduling_application.dao.Role;
import com.projekt.event_scheduling_application.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


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
}
