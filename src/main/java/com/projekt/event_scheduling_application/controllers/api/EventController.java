package com.projekt.event_scheduling_application.controllers.api;

import com.projekt.event_scheduling_application.domain.Event;
import com.projekt.event_scheduling_application.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/esa/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<Event> showAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{name}")
    public Event getEventWithName(@PathVariable(name = "name") String name) {
        return eventService.findByName(name);
    }
}
