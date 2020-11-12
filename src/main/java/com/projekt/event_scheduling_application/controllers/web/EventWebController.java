package com.projekt.event_scheduling_application.controllers.web;

import com.projekt.event_scheduling_application.controllers.api.EventController;
import com.projekt.event_scheduling_application.controllers.api.UserController;
import com.projekt.event_scheduling_application.model.EventForm;
import com.projekt.event_scheduling_application.model.UserForm;
import com.projekt.event_scheduling_application.services.EventService;
import com.projekt.event_scheduling_application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
                                 final Errors errors) {
        if (errors.hasErrors()) {
            return "event";
        }
        eventService.createEvent(eventForm);
        return "redirect:/esa/event";
    }
}
