package services.mapper;

import dao.Event;

import dao.User;
import lombok.RequiredArgsConstructor;
import model.EventForm;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private User user;

    public Event fromEventFormToEvent(final EventForm eventForm){

        final Event event = Event.builder()
                .name(eventForm.getName())
                .date(eventForm.getDate())
                .startTime(eventForm.getStartTime())
                .endTime(eventForm.getEndTime())
                .description(eventForm.getDescription())
                .eventAdmin(user)
                .build();
        return event;
    }
}
