package com.example.incident.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.incident.entity.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    boolean existsByIncidentId(String incidentId);
    Optional<Incident> findByIncidentId(String incidentId);
    Optional<Incident> findByIncidentIdAndUserId(String incidentId, Long userId);
    List<Incident> findAllByUserIdOrderByReportedDateTimeDesc(Long userId);
}
