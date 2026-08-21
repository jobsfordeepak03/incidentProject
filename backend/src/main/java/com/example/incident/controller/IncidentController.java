package com.example.incident.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.incident.dto.*;
import com.example.incident.service.IncidentService;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {
    private final IncidentService incidentService;

    @PostMapping
    public ResponseEntity<IncidentResponse> create(@Valid @RequestBody IncidentRequest request,
                                                     Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(incidentService.create(request, auth));
    }

    @GetMapping
    public List<IncidentResponse> all(Authentication auth) {
        return incidentService.getMyIncidents(auth);
    }

    @GetMapping("/search")
    public IncidentResponse search(@RequestParam String incidentId, Authentication auth) {
        return incidentService.search(incidentId, auth);
    }

    @GetMapping("/{incidentId}")
    public IncidentResponse get(@PathVariable String incidentId, Authentication auth) {
        return incidentService.getById(incidentId, auth);
    }

    @PutMapping("/{incidentId}")
    public IncidentResponse update(@PathVariable String incidentId,
                                   @Valid @RequestBody IncidentRequest request,
                                   Authentication auth) {
        return incidentService.update(incidentId, request, auth);
    }

    @DeleteMapping("/{incidentId}")
    public ResponseEntity<?> delete(@PathVariable String incidentId, Authentication auth) {
        incidentService.delete(incidentId, auth);
        return ResponseEntity.noContent().build();
    }
}
