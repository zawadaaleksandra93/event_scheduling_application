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
    String userName1 = "test@test.com";
    String userName2 = "test2@test.com";
    String userPass1 = "Pass123";
    String userPass2 = "Pass456";
    String userNick1 = "Stefan";
    String userNick2 = "Tomasz";

    String eventName1 = "first event1";
    String eventName2 = "second event";
    LocalDate eventDay1 = LocalDate.of(2020,12,14);
    LocalDate eventDay2 = LocalDate.of(2021,4,6);
    LocalTime eventStart1 = LocalTime.of(12,00);
    LocalTime eventStart2 = LocalTime.of(14,00);
    LocalTime eventEnd1 = LocalTime.of(13,30);
    LocalTime eventEnd2 = LocalTime.of(15,30);
    String eventDescription1 = "this is first test event";
    String eventDescription2 = "this is second test event";

}
