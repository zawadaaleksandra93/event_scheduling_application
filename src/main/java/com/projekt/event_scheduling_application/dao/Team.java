package com.projekt.event_scheduling_application.dao;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "team")
public class Team {

    @Id
    @NotNull
    @Column(name = "team")
    private String teamName;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "user_login")
    private User teamLeader;

    @ToString.Exclude
    @ManyToMany(mappedBy = "team")
    private List<User> teamMembers;
}
