package com.projekt.event_scheduling_application.repositories;

import com.projekt.event_scheduling_application.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CodeRepository extends JpaRepository<Code, UUID> {
}
