package com.example.incident.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "incidents", uniqueConstraints = {
        @UniqueConstraint(name = "uk_incident_id", columnNames = "incident_id")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "incident_id", nullable = false, unique = true, length = 20)
    private String incidentId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrganizationType organizationType;

    @Column(nullable = false, length = 120)
    private String reporterName;

    @Column(nullable = false, length = 180)
    private String reporterEmail;

    @Column(nullable = false, length = 30)
    private String reporterPhone;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String incidentDetails;

    @Column(nullable = false)
    private LocalDateTime reportedDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private IncidentStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
