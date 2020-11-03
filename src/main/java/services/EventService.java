package services;

import dao.Event;
import lombok.RequiredArgsConstructor;
import model.EventForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.EventRepository;
import services.mapper.EventMapper;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class EventService {
    private EventRepository eventRepository;
    private EventMapper eventMapper;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    public void createEvent(final EventForm eventForm){
        Event event = eventMapper.fromEventFormToEvent(eventForm);
        eventRepository.save(event);
    }
    public Event findByName(final String name){
        return (Event) eventRepository.findById(name)
                .orElseThrow();
    }
}
