package com.projekt.event_scheduling_application.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "events")
public class Event {

    @Id
    @NotNull
    @Column(name = "event_name")
    private String name;

    @NotNull
    @Column(name = "event_date")
    private LocalDate date;

    @NotNull
    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    @NotNull
    private LocalTime endTime;

    @NotNull
    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "user_login")
    private User eventAdmin;

    @ToString.Exclude
    @ManyToMany (mappedBy = "listOfSignedOnEvents")
    private List<User> listOfParticipants;


}


