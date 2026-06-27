package com.rescuepaw.controller;

import com.rescuepaw.dto.VolunteerApplicationDTO;
import com.rescuepaw.entity.VolunteerApplication;
import com.rescuepaw.service.VolunteerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * VolunteerController
 * POST /volunteer/apply   → VolunteerPage.jsx form submission
 * GET  /volunteer         → Admin: view all volunteer applications
 */
@RestController
@RequestMapping("/volunteer")
@RequiredArgsConstructor
public class VolunteerController {

    private final VolunteerService volunteerService;

    @PostMapping("/apply")
    public ResponseEntity<?> apply(@Valid @RequestBody VolunteerApplicationDTO dto) {
        try {
            VolunteerApplication saved = volunteerService.apply(dto);
            return ResponseEntity.ok(Map.of(
                "message", "Thank you for volunteering! We'll contact you soon.",
                "applicationId", saved.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<VolunteerApplication>> getAll() {
        return ResponseEntity.ok(volunteerService.getAll());
    }
}
