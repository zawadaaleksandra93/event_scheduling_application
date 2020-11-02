package services;

import dao.User;
import lombok.RequiredArgsConstructor;
import model.UserForm;
import model.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User fromUserFormToUser(final UserForm userForm) {

        final User user = User.builder()
                .email(userForm.getLogin())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .nick(userForm.getNick())
                .userStatus(UserStatus.ACTIVATED)
                .build();

        return user;
    }
}
