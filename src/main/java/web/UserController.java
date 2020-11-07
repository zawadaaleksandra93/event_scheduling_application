package web;

import dao.User;
import lombok.RequiredArgsConstructor;
import model.UserForm;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/esa/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private UserService userService;


    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUserWithEmail(@PathVariable(name = "email") String email) throws Exception {
        return userService.findByEmail(email);
    }

    @GetMapping("/{nick}")
    public User getUserWithNick(@PathVariable(name = "nick") String nick) throws Exception {
        return userService.findByNick(nick);
    }

  /*  @GetMapping("/create")
    public String getUserForm(Model model){
        model.addAttribute("newUser", new User());
        return "users";
    }



   */

    @PostMapping("/create")
    public String createNewUser(@Valid @ModelAttribute(name = "userForm") final UserForm userForm, final Errors errors) {

        if (errors.hasErrors()) {
            return "user_form";
        }
        userService.createUser(userForm);
        return "redirect:/esa/users";
    }

}
