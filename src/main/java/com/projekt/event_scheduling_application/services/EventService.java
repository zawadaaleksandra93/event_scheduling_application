package com.projekt.event_scheduling_application.services;

import com.projekt.event_scheduling_application.domain.Code;
import com.projekt.event_scheduling_application.domain.Event;
import com.projekt.event_scheduling_application.domain.User;
import com.projekt.event_scheduling_application.exceptions.ESAException;
import com.projekt.event_scheduling_application.mailConfirmation.ESAMailSender;
import com.projekt.event_scheduling_application.model.ApprovalForm;
import com.projekt.event_scheduling_application.model.EventForm;
import com.projekt.event_scheduling_application.model.EventSearchForm;
import com.projekt.event_scheduling_application.repositories.CodeRepository;
import com.projekt.event_scheduling_application.repositories.UserRepository;
import com.projekt.event_scheduling_application.services.mapper.SearchEventMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.projekt.event_scheduling_application.repositories.EventRepository;
import com.projekt.event_scheduling_application.services.mapper.EventMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final SearchEventMapper searchEventMapper;
    private final UserRepository userRepository;
    private final ESAMailSender ESAMailSender;
    private final CodeRepository codeRepository;


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void createEvent(final EventForm eventForm, String userCreatingEvent) {
        Event event = eventMapper.fromEventFormToEvent(eventForm, userCreatingEvent);
        eventRepository.save(event);
    }

    public Event findEventByDetailsFromUser(final EventSearchForm eventSearchForm) {
        Event searchEvent = searchEventMapper.fromEventSearchFormToEvent(eventSearchForm);
        Event expectedEvent;

        if (searchEvent.getName() == null && searchEvent.getDate() == null) {
            throw new ESAException("there is no data to search. Please provide event name or event Date");
        } else if (searchEvent.getName() != null) {
            return findByName(searchEvent.getName());
        } else
            return findByDate(searchEvent.getDate());
    }

    public Event findByName(final String name) {
        return eventRepository.findById(name)
                .orElseThrow(()->new ESAException(String
                        .format("There is no event: %s",name)));
    }

    public Event findByDate(final LocalDate eventDate) {
        return eventRepository.findAll()
                .stream()
                .filter(event -> event.getDate().equals(eventDate))
                .findAny().orElseThrow(()-> new ESAException(
                "There is no event scheduled for: "+eventDate));
    }

    public void assignForEvent(Event event, String userToBeAssigned) {

        User userToAssign = userRepository.findById(userToBeAssigned).orElseThrow(() ->
                new ESAException(String.format("there is no user: %s", userToBeAssigned)));

        List<User> participantsList = event.getListOfParticipants();
        if (participantsList.contains(userToAssign)) {
            throw new ESAException("User is already assigned");
        }
        participantsList.add(userToAssign);
        userToAssign.getListOfSignedOnEvents().add(event);
        userRepository.save(userToAssign);
    }

    public User findTeamManager(String userToBeAssigned) {
        final User user = userRepository.findById(userToBeAssigned).orElseThrow(() ->
                new ESAException(String.format("there is no user: %s", userToBeAssigned)));
        User teamManager = user.getTeam().stream()
                .filter(team -> !team.getTeamLeader().equals(user))
                .map(team -> team.getTeamLeader())
                .findFirst().orElseThrow(() ->
                        new ESAException(String.format("there is no team manager for team: %s"
                                , user.getTeam())));
        return teamManager;
    }

    public void sendRequestToAssignToManager(String eventName, String userToBeAssigned) {
        final Code code = new Code(userToBeAssigned, eventName,LocalDateTime.now().plusDays(1));
        final Code savedCode = codeRepository.save(code);
        final User teamManager = findTeamManager(userToBeAssigned);
        String approvalSubject = "ESA: Manager approval required";
        String approvalMessage = String
                .format("please accept participation of %s in an event: %s. //n" +
                        //http://localhost:8080 wrzucic jako properties konfiguracyjne sciaga u Matyldy
                                "Please click the link: http://localhost:8080/esa/event/assign/approval?code=%s " +
                                "to accept request"
                        , userToBeAssigned, eventName, savedCode.getId());
        ESAMailSender.sendEmail(teamManager.getEmail(),
                approvalSubject, approvalMessage);
    }

    public void sendInformationThatRequestHadBeenSend(String eventName, String userToBeAssigned) {
        final User teamManager = findTeamManager(userToBeAssigned);
        String teamManagerName = teamManager.getEmail();
        String messageSubject = String.format("ESA: managers approval required for an event: %s"
                , eventName);
        String messageContent = String
                .format("Request of participation in an event: %s has been send to %s"
                        , eventName, teamManagerName);
        ESAMailSender.sendEmail(userToBeAssigned, messageSubject, messageContent);
    }

    public void managersApproval(ApprovalForm approvalForm, Event event,
                                 String userToBeAssigned, Code code) {
        final User teamManager = findTeamManager(userToBeAssigned);
        String teamManagerName = teamManager.getEmail();
        String messageSubject = String.format("ESA: managers approval required for an event: %s"
                , event.getName());
        if (code.getValidTill().isAfter(LocalDateTime.now())) {
            if (approvalForm.isApprove()) {
                assignForEvent(event, userToBeAssigned);

                String messageContent = String
                        .format("Request of participation in an event: %s has been approved by %s"
                                , event.getName(), teamManager);
                ESAMailSender.sendEmail(userToBeAssigned, messageSubject, messageContent);
            } else {
                String messageContent = String
                        .format("Request of participation in an event: %s has been denied by %s"
                                , event.getName(), teamManager.getEmail());
                ESAMailSender.sendEmail(userToBeAssigned, messageSubject, messageContent);
            }
        }else throw new ESAException("provided path is no longer valid");
    }
}
