package com.projekt.event_scheduling_application.services.mapper;

import com.projekt.event_scheduling_application.dao.Event;
import com.projekt.event_scheduling_application.model.EventForm;
import com.projekt.event_scheduling_application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class EventMapper {

private final UserRepository userRepository;

    public Event fromEventFormToEvent(final EventForm eventForm, final String userLogin){

        final Event event = Event.builder()
                .name(eventForm.getName())
                .date(LocalDate.parse(eventForm.getDate(), DateTimeFormatter.ISO_DATE))
                .startTime(eventForm.getStartTime())
                .endTime(eventForm.getEndTime())
                .description(eventForm.getDescription())
                .eventAdmin(userRepository.findById(userLogin).orElseThrow())
                .build();
        return event;
    }
}
