package com.projekt.event_scheduling_application.controllers.api;

import com.projekt.event_scheduling_application.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.projekt.event_scheduling_application.services.UserService;

import java.util.List;


@RequestMapping("/api/esa/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // @Secured("ROLE_ADMIN")
    @GetMapping
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUserWithEmail(@PathVariable(name = "email") String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/search/{nick}")
    public User getUserWithNick(@PathVariable(name = "nick") String nick) {
        return userService.findByNick(nick);
    }


}
