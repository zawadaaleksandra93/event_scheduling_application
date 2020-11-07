package web;

import lombok.RequiredArgsConstructor;
import model.EventForm;
import model.UserForm;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.EventService;

import javax.validation.Valid;

@RestController
@RequestMapping("/esa/event")
@RequiredArgsConstructor
public class EventController {

    private EventService eventService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public String createNewEvent(@Valid @ModelAttribute(name = "eventForm") final EventForm eventForm, final Errors errors){
        if (errors.hasErrors()){
            return "event";
        }
        eventService.createEvent(eventForm);
        return "redirect:/event";
    }


}
