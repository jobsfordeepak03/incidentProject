package com.example.incident.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;
import lombok.Data;
import com.example.incident.entity.*;

@Data
public class IncidentRequest {
    @NotNull
    private OrganizationType organizationType;

    @NotBlank
    private String incidentDetails;

    @NotNull
    private LocalDateTime reportedDateTime;

    @NotNull
    private Priority priority;

    @NotNull
    private IncidentStatus status;
}
