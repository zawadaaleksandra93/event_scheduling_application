package com.projekt.event_scheduling_application.controllers.web;

import com.projekt.event_scheduling_application.model.UserForm;
import com.projekt.event_scheduling_application.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserWebControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void createMockMVC() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void shouldGetUserForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/esa/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("users"))
                .andExpect(MockMvcResultMatchers.model().attribute("userForm", new UserForm()));
    }

    @Test
    void shouldCreateNewUser() throws Exception {
        UserForm userForm = UserForm.builder()
                .login("test@test.com")
                .password("12345678")
                .confirmPassword("12345678")
                .nick("Stefan")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/esa/users/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("login", userForm.getLogin())
                .param("password", userForm.getPassword())
                .param("confirmPassword", userForm.getConfirmPassword())
                .param("nick", userForm.getNick()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/esa/users"));

        assertThat(userRepository.findById(userForm.getLogin())).isNotEmpty();
    }

    @Test
    void shouldNotCreateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/esa/users/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("users"));

    }

}