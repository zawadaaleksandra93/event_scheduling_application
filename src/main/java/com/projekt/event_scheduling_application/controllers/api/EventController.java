package com.projekt.event_scheduling_application.controllers.api;

import com.projekt.event_scheduling_application.dao.Event;
import com.projekt.event_scheduling_application.dao.User;
import com.projekt.event_scheduling_application.model.EventForm;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.projekt.event_scheduling_application.services.EventService;

import javax.validation.Valid;
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
    public Event getUserWithEmail(@PathVariable(name = "name") String name) {
        return eventService.findByName(name);
    }



}
