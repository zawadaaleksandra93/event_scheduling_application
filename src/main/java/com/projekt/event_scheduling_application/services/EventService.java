package com.projekt.event_scheduling_application.services;

import com.projekt.event_scheduling_application.dao.Event;
import com.projekt.event_scheduling_application.dao.Team;
import com.projekt.event_scheduling_application.dao.TeamRole;
import com.projekt.event_scheduling_application.dao.User;
import com.projekt.event_scheduling_application.exceptions.UserAlreadyAddedException;
import com.projekt.event_scheduling_application.mailConfirmation.ApprovalRequestMail;
import com.projekt.event_scheduling_application.mailConfirmation.EmailConst;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserRepository userRepository;
    private final ApprovalRequestMail approvalRequestMail;

    private EmailConst emailConst;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void createEvent(final EventForm eventForm, String userCreatingEvent) {
        Event event = eventMapper.fromEventFormToEvent(eventForm, userCreatingEvent);
        eventRepository.save(event);
    }

    public Event findByName(final String name) {
        return eventRepository.findById(name)
                .orElseThrow();
    }

    public Event findByDate(final LocalDate eventDate) {
        return null;
    }

    public void assignForEvent(Event event, String userToBeAssigned) {

        User userToAssign = userRepository.findById(userToBeAssigned).orElseThrow();

        List<User> participantsList = event.getListOfParticipants();
        if (participantsList.contains(userToAssign)) {
            throw new UserAlreadyAddedException();
        }
        participantsList.add(userToAssign);
        userToAssign.getListOfSignedOnEvents().add(event);
        userRepository.save(userToAssign);
    }

    public User findTeamManager(String userToBeAssigned) {
        final User user = userRepository.findById(userToBeAssigned).orElseThrow();
        User teamManager = user.getTeam().stream()
                .filter(team -> !team.getTeamLeader().equals(user))
                .map(team -> team.getTeamLeader())
                .findFirst().orElseThrow();
        return teamManager;
    }

    public void sendRequestToAssignToManager(String eventName, String userToBeAssigned) {
        final User teamManager = findTeamManager(userToBeAssigned);
        String approvalSubject = "ESA: Manager approval required";
        String approvalMessage = String
                .format("please accept participation of %s in an event: %s. Please go to path: to accept request"
                        ,userToBeAssigned, eventName);
        approvalRequestMail.sendEmail(teamManager.getEmail()
                ,approvalSubject, approvalMessage);
    }

    public void sendInformationThatRequestHadBeenSend(String eventName, String userToBeAssigned) {
        final User teamManager = findTeamManager(userToBeAssigned);
        String teamManagerName = teamManager.getEmail();
        String messageSubject = String.format("ESA: managers approval required for %s", eventName);
        String messageContent = String
                .format("Request of participation in %s has been send to %s"
                        ,eventName,teamManagerName);
        approvalRequestMail.sendEmail(userToBeAssigned,messageSubject,messageContent);
    }

}
