package com.projekt.event_scheduling_application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventForm {


    @NotNull(message = "name has to be provided")
    private String name;

    @NotNull (message = "date has to be provided")
    private String date;

    @NotNull (message = "Start time has to be provided")
    private LocalTime startTime;

    @NotNull (message = "End time has to be provided")
    private LocalTime endTime;

    @NotNull (message = "Start time has to be provided")
    @Length(min = 2, message = "description should has at least 2 signs")
    private String description;
}
