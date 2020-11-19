package com.projekt.event_scheduling_application.services;

import com.projekt.event_scheduling_application.dao.Team;
import com.projekt.event_scheduling_application.dao.TeamRole;
import com.projekt.event_scheduling_application.dao.User;
import com.projekt.event_scheduling_application.exceptions.ESAException;
import com.projekt.event_scheduling_application.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import com.projekt.event_scheduling_application.model.UserForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.projekt.event_scheduling_application.repositories.UserRepository;
import com.projekt.event_scheduling_application.services.mapper.UserMapper;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TeamRepository teamRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void updateTeamManager(User user) {
        final User existingUser = userRepository.findById(user.getEmail()).orElseThrow();
        existingUser.setManagedTeam(user.getTeam());
        Team team = user.getTeam().stream().findFirst().orElseThrow();
        team.setTeamLeader(existingUser);
        userRepository.save(existingUser);
        teamRepository.save(team);

    }

    public void createUser(UserForm userForm) {
        final User user = userMapper.fromUserFormToUser(userForm);
        userRepository.save(user);
        if (user.getTeamRole().equals(TeamRole.MANAGER)){
            updateTeamManager(user);
        }
    }

    public User findByEmail(final String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new ESAException(String
                                .format("User with email: %s does not exist", email)));
    }

    public User findByNick(final String nick) {
        return userRepository.findByNick(nick)
                .orElseThrow(() -> new ESAException(String
                        .format("User with nick: %s does not exist", nick)));
    }


    public void deleteUser(final String email) {
        userRepository.deleteById(email);
    }
}
