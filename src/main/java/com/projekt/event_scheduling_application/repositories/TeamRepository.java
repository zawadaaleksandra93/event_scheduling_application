package com.projekt.event_scheduling_application.repositories;

import com.projekt.event_scheduling_application.dao.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {
}
