package com.projekt.event_scheduling_application.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "codes")
public class Code {

    @GeneratedValue
    @Id
    private UUID id;
    private String userName;
    private String eventName;

    private LocalDateTime validTill;

    public Code(String userName, String eventName) {
        this.userName = userName;
        this.eventName = eventName;
    }
}
