package com.projekt.event_scheduling_application.repositories;

import com.projekt.event_scheduling_application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByNick(String nick);

}
