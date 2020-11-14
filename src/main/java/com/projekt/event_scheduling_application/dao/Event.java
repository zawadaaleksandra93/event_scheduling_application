package com.projekt.event_scheduling_application.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    private User eventAdmin;

    @ManyToMany (mappedBy = "events")
    private List<User> listOfParticipants;


}


