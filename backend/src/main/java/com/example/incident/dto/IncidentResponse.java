package com.example.incident.dto;

import java.time.LocalDateTime;
import lombok.*;
import com.example.incident.entity.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class IncidentResponse {
    private String incidentId;
    private OrganizationType organizationType;
    private String reporterName;
    private String reporterEmail;
    private String reporterPhone;
    private String incidentDetails;
    private LocalDateTime reportedDateTime;
    private Priority priority;
    private IncidentStatus status;
}
