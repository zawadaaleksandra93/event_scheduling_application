package com.projekt.event_scheduling_application.controllers.web;

import com.projekt.event_scheduling_application.model.UserForm;
import com.projekt.event_scheduling_application.model.UserStatus;
import com.projekt.event_scheduling_application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/esa/users")
@Controller
@RequiredArgsConstructor
public class UserWebController {

    private final UserService userService;

    @GetMapping
    public String getUserForm(ModelMap modelMap){
        modelMap.addAttribute("userForm", new UserForm());
        return "users";
    }


    @PostMapping("/create")
    public String createNewUser(@Valid @ModelAttribute(name = "userForm") final UserForm userForm, final Errors errors) {

        if (errors.hasErrors()) {
            return "users";
        }
        userService.createUser(userForm);
        return "redirect:/esa/users";
    }
}
