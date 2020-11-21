package com.projekt.event_scheduling_application.services.adapter;

import com.projekt.event_scheduling_application.domain.Role;
import com.projekt.event_scheduling_application.domain.User;
import com.projekt.event_scheduling_application.model.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserAdapter implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Role> roleList = new ArrayList<>();
         roleList.add(user.getRole());

        return roleList.stream()
                 .map(role -> new SimpleGrantedAuthority(role.name()))
                 .collect(Collectors.toUnmodifiableList());

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getUserStatus() != UserStatus.BLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
