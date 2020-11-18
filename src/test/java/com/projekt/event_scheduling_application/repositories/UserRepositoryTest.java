package com.projekt.event_scheduling_application.repositories;


import com.projekt.event_scheduling_application.dao.Role;
import com.projekt.event_scheduling_application.dao.User;
import com.projekt.event_scheduling_application.model.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindByNick() {
        //given
        User user = User.builder()
                .email("test@test.com")
                .password("pass1234")
                .nick("Stefan")
                .userStatus(UserStatus.ACTIVATED)
                .role(Role.REGULAR_USER)
                .build();
        userRepository.save(user);

        //when
        final Optional<User> userByNick = userRepository.findByNick(user.getNick());

        //then
        assertThat(userByNick).isNotEmpty().hasValue(user);
    }

    @Test
    void shouldTest() {
        assertThat(userRepository.count()).isEqualTo(0);
    }


}