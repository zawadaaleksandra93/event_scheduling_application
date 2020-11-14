package com.projekt.event_scheduling_application.controllers.web;

import com.projekt.event_scheduling_application.controllers.api.EventController;
import com.projekt.event_scheduling_application.controllers.api.UserController;
import com.projekt.event_scheduling_application.dao.Event;
import com.projekt.event_scheduling_application.model.EventForm;
import com.projekt.event_scheduling_application.model.UserForm;
import com.projekt.event_scheduling_application.services.EventService;
import com.projekt.event_scheduling_application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequestMapping("/esa/event")
@Controller
@RequiredArgsConstructor
public class EventWebController {

    private final EventService eventService;
    private final EventController eventController;

    @GetMapping
    public String getEventForm(ModelMap modelMap) {
        modelMap.addAttribute("eventForm", new EventForm());
        return "event";
    }

    @PostMapping("/create")
    public String createNewEvent(@Valid @ModelAttribute(name = "eventForm") final EventForm eventForm,
                                 final Errors errors, @AuthenticationPrincipal Principal principal) {
        if (errors.hasErrors()) {
            return "event";
        }

        eventService.createEvent(eventForm, principal.getName());
        return "redirect:/esa/event";
    }

    @GetMapping("/getAllEvents")
    public String getAllEvents(Model model) {
        List<Event> allEvents = eventController.showAllEvents();
        model.addAttribute("allEvents", allEvents);

        return "all_events";

    }

    @PostMapping("/assign")
    public String assignForEvent(@Valid @ModelAttribute(name = "allEvents") final Event event,
                                 final Errors errors, @AuthenticationPrincipal Principal principal) {
        if (errors.hasErrors()) {
            return "all_events";
        }
        eventService.assignForEvent(event, principal.getName());
        return "redirect:/esa/event";
    }

    @GetMapping("/{name}")
    public String getEventDetailsByName(@PathVariable String name, Model model) {
        Event eventWithGivenName = eventService.findByName(name);
        model.addAttribute("allEvents", eventWithGivenName);

        return "event_view";

    }



}
