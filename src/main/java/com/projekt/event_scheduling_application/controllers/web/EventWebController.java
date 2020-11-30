package com.projekt.event_scheduling_application.controllers.web;

import com.projekt.event_scheduling_application.controllers.api.EventController;
import com.projekt.event_scheduling_application.domain.Code;
import com.projekt.event_scheduling_application.domain.Event;
import com.projekt.event_scheduling_application.domain.User;
import com.projekt.event_scheduling_application.model.ApprovalForm;
import com.projekt.event_scheduling_application.model.EventForm;
import com.projekt.event_scheduling_application.model.EventSearchForm;
import com.projekt.event_scheduling_application.services.CodeService;
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
import java.util.UUID;

@RequestMapping("/esa/event")
@Controller
@RequiredArgsConstructor
public class EventWebController {

    private final EventService eventService;
    private final EventController eventController;
    private final UserService userService;
    private final CodeService codeService;

    @GetMapping
    public String getWelcomeForm() {
        return "welcome";
    }

    //dostepne tylko dla admina, nie dla regular
    @GetMapping("/event_form")
    public String getEventForm(ModelMap modelMap) {
        modelMap.addAttribute("eventForm", new EventForm());
        return "event";
    }
    //dostepne tylko dla admina, nie dla regular
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

    @GetMapping("/{name}")
    public String getEventDetailsByName(@PathVariable String name, Model model) {
        Event eventWithGivenName = eventService.findByName(name);
        model.addAttribute("allEvents", eventWithGivenName);
        return "event_view";
    }

    @GetMapping("/event_search_form")
    public String getEventSearchForm(ModelMap modelMap) {
        modelMap.addAttribute("eventSearchForm", new EventSearchForm());
        return "event_search";
    }

    @PostMapping("/search")
    public String searchAnEvent(@Valid @ModelAttribute(name = "eventSearchForm")
                                    final EventSearchForm eventSearchForm,
                                 final Errors errors) {
        if (errors.hasErrors()) {
            return "event_search";
        }
        final Event eventByDetailsFromUser = eventService.findEventByDetailsFromUser(eventSearchForm);
        String eventName = eventByDetailsFromUser.getName();
        return "redirect:/esa/event/{name}";
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

    @GetMapping("/assign/approval")
    public String getApprovalForm(ModelMap modelMap,
                                  @RequestParam(name = "code") UUID pathCode) {
        final String eventName = codeService.findEventName(pathCode);
        final String name = codeService.findUserName(pathCode);
        final User user = userService.findByEmail(name);
        final Event event = eventService.findByName(eventName);
        modelMap.addAttribute("approvalForm", new ApprovalForm());
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("event", event);
        modelMap.addAttribute("code", pathCode);
        return "approval_form";
    }

    @PostMapping("/assign/{name}/{eventName}/approval/t-f")
    public String assignForEvent(@PathVariable final String name,
                                 @PathVariable final String eventName,
                                 @Valid @ModelAttribute(name = "approval") final ApprovalForm approvalForm,
                                 @RequestParam(name = "code") UUID pathCode) {
        final Code code = codeService.findCode(pathCode);
        Event event = eventService.findByName(eventName);
        eventService.managersApproval(approvalForm, event, name, code);
        return "redirect:/esa/event";
    }
}
