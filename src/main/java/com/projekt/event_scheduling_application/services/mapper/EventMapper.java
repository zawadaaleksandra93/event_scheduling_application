package com.projekt.event_scheduling_application.services.mapper;

import com.projekt.event_scheduling_application.dao.Event;
import com.projekt.event_scheduling_application.dao.User;
import com.projekt.event_scheduling_application.model.EventForm;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMapper {



    public Event fromEventFormToEvent(final EventForm eventForm){

        final Event event = Event.builder()
                .name(eventForm.getName())
                .date(eventForm.getDate())
                .startTime(eventForm.getStartTime())
                .endTime(eventForm.getEndTime())
                .description(eventForm.getDescription())
                .eventAdmin(null)
                .build();
        return event;
    }
}
