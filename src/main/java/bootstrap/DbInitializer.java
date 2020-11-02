package bootstrap;

import configuration.UserProperties;
import dao.Role;
import dao.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Component
public class DbInitializer {

    private String email;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserProperties userProperties;

@EventListener(ContextRefreshedEvent.class)
public void onClick(){
userRepository.save(new User(userProperties.getLogin(),
        passwordEncoder.encode(userProperties.getPassword()),
        userProperties.getNick(),
        userProperties.getUserRole(),
        List.of(new Role("ROLE_ADMIN", "admin group - admin can add an event", true,List.of()))));
}
}
