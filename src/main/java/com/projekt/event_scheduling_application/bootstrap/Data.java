package com.projekt.event_scheduling_application.bootstrap;

import com.projekt.event_scheduling_application.dao.Event;
import com.projekt.event_scheduling_application.dao.Role;
import com.projekt.event_scheduling_application.dao.User;
import com.projekt.event_scheduling_application.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Getter
public class Data {
    String USER_NAME1 = "test@test.com";
    String USER_NAME2 = "test2@test.com";
    String USER_PASS1 = "Pass123";
    String USER_PASS2 = "Pass456";
    String USER_NICK1 = "Stefan";
    String USER_NICK2 = "Tomasz";

    String EVENT_NAME1 = "first event1";
    String EVENT_NAME2 = "second event";
    LocalDate EVENT_DAY1 = LocalDate.of(2020,12,14);
    LocalDate EVENT_DAY2 = LocalDate.of(2021,4,6);
    LocalTime EVENT_START1 = LocalTime.of(12,00);
    LocalTime EVENT_START2 = LocalTime.of(14,00);
    LocalTime EVENT_END1 = LocalTime.of(13,30);
    LocalTime EVENT_END2 = LocalTime.of(15,30);
    String EVENT_DESCRIPTION1 = "this is first test event";
    String EVENT_DESCRIPTION2 = "this is second test event";

}
