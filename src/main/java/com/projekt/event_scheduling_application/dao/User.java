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


    @Enumerated(EnumType.STRING)
    @Column(name = "team_role")
    private TeamRole teamRole;

    @OneToMany(mappedBy = "teamLeader")
    private List<Team> managedTeam;

    @OneToMany(mappedBy = "eventAdmin")
    private List<Event> createdEvents;

    @ManyToMany
    @JoinTable(name = "events_participants",
            joinColumns = @JoinColumn(name = "user_email",referencedColumnName = "login"),
    inverseJoinColumns = @JoinColumn(name = "event_id",referencedColumnName = "event_name"))
    private List<Event> listOfSignedOnEvents;

    @NotNull
    @ManyToMany
    @JoinTable(name = "Team_members",
            joinColumns = @JoinColumn(name = "user_email",referencedColumnName = "login"),
            inverseJoinColumns = @JoinColumn(name = "team",referencedColumnName = "team"))
    private List<Team> team;

}
