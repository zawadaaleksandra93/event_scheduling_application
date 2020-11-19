package com.projekt.event_scheduling_application.controllers.web;

import com.projekt.event_scheduling_application.controllers.api.EventController;
import com.projekt.event_scheduling_application.controllers.api.UserController;
import com.projekt.event_scheduling_application.dao.Event;
import com.projekt.event_scheduling_application.dao.Team;
import com.projekt.event_scheduling_application.dao.User;
import com.projekt.event_scheduling_application.mailConfirmation.ApprovalRequestMail;
import com.projekt.event_scheduling_application.model.ApprovalForm;
import com.projekt.event_scheduling_application.model.EventForm;
import com.projekt.event_scheduling_application.model.UserForm;
import com.projekt.event_scheduling_application.services.EventService;
import com.projekt.event_scheduling_application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
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
    private final UserService userService;

    @GetMapping
    public String getWelcomeForm() {
        return "welcome";

    }

    @GetMapping("/event_form")
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

    @GetMapping("/assign/{name}")
    public String sendRequestToManager(@PathVariable final String name,
                                       @AuthenticationPrincipal Principal principal) {
        Event event = eventService.findByName(name);
        String eventName = event.getName();
        User user = userService.findByEmail(principal.getName());
        String userEmail = user.getEmail();
        eventService.sendRequestToAssignToManager(eventName, userEmail);
        eventService.sendInformationThatRequestHadBeenSend(eventName, userEmail);
        return "request_had_been_send";
    }

    @GetMapping("/approval")
    public String getApprovalForm(ModelMap modelMap) {
        modelMap.addAttribute("approval", new ApprovalForm());
        return "approval_form";
    }

    @PostMapping("/assign/{name}/{eventName}/approval")
    public String assignForEvent(@PathVariable final String name,
                                 @PathVariable final String eventName,
                                 @Valid @ModelAttribute(name = "approval")
                                 final ApprovalForm approvalForm
                                // ,@AuthenticationPrincipal Principal principal
                                 ) {

        Event event = eventService.findByName(eventName);
        eventService.managersApproval(approvalForm,event,name);
        //eventService.assignForEvent(event, principal.getName());

        return "redirect:/esa/event";
    }


    @GetMapping("/{name}")
    public String getEventDetailsByName(@PathVariable String name, Model model) {
        Event eventWithGivenName = eventService.findByName(name);
        model.addAttribute("allEvents", eventWithGivenName);

        return "event_view";
    }


}
