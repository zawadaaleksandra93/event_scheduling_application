package com.projekt.event_scheduling_application.services;

import com.projekt.event_scheduling_application.services.adapter.UserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return new UserAdapter(userService.findByEmail(email));
    }
}
