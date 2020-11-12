package com.projekt.event_scheduling_application.configuration;

import com.projekt.event_scheduling_application.services.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService customUserDetailService;

    @Override
    protected UserDetailsService userDetailsService() {
        return customUserDetailService;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //*,**,?
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated() //tymczasowe
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout()
                .and()
                .csrf().disable()//ignoringAntMatchers("/h2/**")
                //.and()
                .headers().frameOptions().disable();

    }

}
