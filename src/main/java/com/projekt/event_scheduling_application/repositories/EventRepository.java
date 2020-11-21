package com.projekt.event_scheduling_application.repositories;

import com.projekt.event_scheduling_application.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository <Event, String> {
}
