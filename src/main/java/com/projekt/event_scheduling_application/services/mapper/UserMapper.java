package com.projekt.event_scheduling_application.services.mapper;

import com.projekt.event_scheduling_application.dao.Role;
import com.projekt.event_scheduling_application.dao.User;
import com.projekt.event_scheduling_application.model.UserStatus;
import lombok.RequiredArgsConstructor;
import com.projekt.event_scheduling_application.model.UserForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class UserMapper {
  //  private final PasswordEncoder passwordEncoder;

    public User fromUserFormToUser(final UserForm userForm) {

        final User user = User.builder()
                .email(userForm.getLogin())
                .password(userForm.getPassword())
                //.password(passwordEncoder.encode(userForm.getPassword()))
                .nick(userForm.getNick())
                .role(Role.REGULAR_USER)
                .userStatus(UserStatus.ACTIVATED)
                .build();

        return user;
    }
}
