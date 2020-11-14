package com.projekt.event_scheduling_application.dao;

import com.projekt.event_scheduling_application.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "users")
public class User {

    @Id
    @NotNull
    @Email
    @Column(name = "login")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "name_to_reflect")
    private String nick;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus userStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "eventAdmin")
    @JoinColumn(name = "event_name")
    private List<Event> createdEvents;

    @ManyToMany
    private List<Event> listOfSignedOnEvents;

}
