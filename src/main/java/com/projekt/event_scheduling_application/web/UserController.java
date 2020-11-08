package com.projekt.event_scheduling_application.web;

import com.projekt.event_scheduling_application.dao.User;
import lombok.RequiredArgsConstructor;
import com.projekt.event_scheduling_application.model.UserForm;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.projekt.event_scheduling_application.services.UserService;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/esa/users")
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

  /*  @GetMapping("/create")
    public String getUserForm(Model com.projekt.event_scheduling_application.model){
        com.projekt.event_scheduling_application.model.addAttribute("newUser", new User());
        return "users";
    }



   */
/*
    @PostMapping("/create")
    public String createNewUser(@Valid @ModelAttribute(name = "userForm") final UserForm userForm, final Errors errors) {

        if (errors.hasErrors()) {
            return "user_form";
        }
        userService.createUser(userForm);
        return "redirect:/esa/users";
    }

 */

}
