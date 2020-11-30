package com.projekt.event_scheduling_application.services.mapper;


import com.projekt.event_scheduling_application.domain.Event;
import com.projekt.event_scheduling_application.model.EventForm;
import com.projekt.event_scheduling_application.model.EventSearchForm;
import com.projekt.event_scheduling_application.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class SearchEventMapper {

    private final UserRepository userRepository;

    public Event fromEventSearchFormToEvent(final EventSearchForm eventSearchForm) {
        return Event.builder()
                .name(eventSearchForm.getName())
                .date(LocalDate.parse(eventSearchForm.getDate(), DateTimeFormatter.ISO_DATE))
                .build();
    }
}
