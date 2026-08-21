package com.example.incident.service;

import java.time.Year;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.incident.dto.*;
import com.example.incident.entity.*;
import com.example.incident.exception.*;
import com.example.incident.repository.*;

@Service @RequiredArgsConstructor
public class IncidentService {
    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;

    private Long userId(Authentication auth) {
        return userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"))
                .getId();
    }

    private User currentUser(Authentication auth) {
        return userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"));
    }

    private String generateUniqueIncidentId() {
        for (int i = 0; i < 100; i++) {
            int number = ThreadLocalRandom.current().nextInt(10000, 100000);
            String id = "RMG" + number + Year.now().getValue();
            if (!incidentRepository.existsByIncidentId(id)) return id;
        }
        throw new BusinessException("Could not generate a unique incident ID");
    }

    @Transactional
    public IncidentResponse create(IncidentRequest req, Authentication auth) {
        User user = currentUser(auth);
        Incident incident = Incident.builder()
                .incidentId(generateUniqueIncidentId())
                .user(user)
                .organizationType(req.getOrganizationType())
                .reporterName(user.getUsername())
                .reporterEmail(user.getEmail())
                .reporterPhone(user.getPhone())
                .incidentDetails(req.getIncidentDetails())
                .reportedDateTime(req.getReportedDateTime())
                .priority(req.getPriority())
                .status(req.getStatus())
                .build();
        return toResponse(incidentRepository.save(incident));
    }

    public List<IncidentResponse> getMyIncidents(Authentication auth) {
        return incidentRepository.findAllByUserIdOrderByReportedDateTimeDesc(userId(auth))
                .stream().map(this::toResponse).toList();
    }

    public IncidentResponse getById(String incidentId, Authentication auth) {
        return toResponse(incidentRepository.findByIncidentIdAndUserId(incidentId, userId(auth))
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found")));
    }

    @Transactional
    public IncidentResponse update(String incidentId, IncidentRequest req, Authentication auth) {
        Incident incident = incidentRepository.findByIncidentIdAndUserId(incidentId, userId(auth))
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found"));

        if (incident.getStatus() == IncidentStatus.CLOSED)
            throw new BusinessException("Closed incident cannot be edited");

        incident.setOrganizationType(req.getOrganizationType());
        incident.setIncidentDetails(req.getIncidentDetails());
        incident.setReportedDateTime(req.getReportedDateTime());
        incident.setPriority(req.getPriority());
        incident.setStatus(req.getStatus());

        return toResponse(incidentRepository.save(incident));
    }

    @Transactional
    public void delete(String incidentId, Authentication auth) {
        Incident incident = incidentRepository.findByIncidentIdAndUserId(incidentId, userId(auth))
                .orElseThrow(() -> new ResourceNotFoundException("Incident not found"));
        if (incident.getStatus() == IncidentStatus.CLOSED)
            throw new BusinessException("Closed incident cannot be deleted");
        incidentRepository.delete(incident);
    }

    public IncidentResponse search(String incidentId, Authentication auth) {
        return getById(incidentId, auth);
    }

    private IncidentResponse toResponse(Incident i) {
        return IncidentResponse.builder()
                .incidentId(i.getIncidentId())
                .organizationType(i.getOrganizationType())
                .reporterName(i.getReporterName())
                .reporterEmail(i.getReporterEmail())
                .reporterPhone(i.getReporterPhone())
                .incidentDetails(i.getIncidentDetails())
                .reportedDateTime(i.getReportedDateTime())
                .priority(i.getPriority())
                .status(i.getStatus())
                .build();
    }
}
