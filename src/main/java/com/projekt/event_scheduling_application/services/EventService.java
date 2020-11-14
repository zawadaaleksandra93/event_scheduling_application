package com.projekt.event_scheduling_application.services;

import com.projekt.event_scheduling_application.dao.Event;
import com.projekt.event_scheduling_application.dao.User;
import com.projekt.event_scheduling_application.model.EventForm;
import com.projekt.event_scheduling_application.repositories.UserRepository;
import com.projekt.event_scheduling_application.services.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.projekt.event_scheduling_application.repositories.EventRepository;
import com.projekt.event_scheduling_application.services.mapper.EventMapper;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserRepository userRepository;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    public void createEvent(final EventForm eventForm, String userCreatingEvent){
        Event event = eventMapper.fromEventFormToEvent(eventForm, userCreatingEvent);
        eventRepository.save(event);
    }
    public Event findByName(final String name){
        return eventRepository.findById(name)
                .orElseThrow();
    }

    public Event findByDate(final LocalDate eventDate){
        return null;
    }

    public void assignForEvent(Event event, String userToBeAssigned){

        User userToAssign = userRepository.findById(userToBeAssigned).orElseThrow();

        event.getListOfParticipants().add(userToAssign);

    }
}
