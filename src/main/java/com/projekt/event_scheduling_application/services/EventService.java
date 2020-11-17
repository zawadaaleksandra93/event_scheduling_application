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

    public User findTeamManager(User user) {
        User teamManager = user.getTeam().stream()
                .filter(team -> team.getTeamMembers().stream()
                        .filter(teamMember -> teamMember.getEmail().equals(user.getEmail()))
                        .filter(teamMember -> teamMember.getTeamRole().equals(TeamRole.REGULAR))
                        .)
                .collect(Collectors.toList());




        return teamManager;
    }

    public void sendRequestToAssignToManager(Event event, String userToBeAssigned) {


        final User user = userRepository.findById(userToBeAssigned).orElseThrow();
        final User teamManager = findTeamManager(user);


        approvalRequestMail.sendEmail(teamManager.getEmail(), emailConst.getAPPROVAL_SUBJECT(), emailConst.getAPPROVAL_MESSAGE());
    }

}
