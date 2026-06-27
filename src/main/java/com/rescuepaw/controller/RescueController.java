package com.rescuepaw.controller;

import com.rescuepaw.dto.RescueRequestDTO;
import com.rescuepaw.entity.RescueRequest;
import com.rescuepaw.service.RescueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * RescueController — handles rescue request submissions
 *
 * POST /rescue          → RescuePage.jsx submits rescue form
 * GET  /rescue          → Admin: view all rescue requests
 * GET  /rescue/{ref}    → Track by reference number
 */
@RestController
@RequestMapping("/rescue")
@RequiredArgsConstructor
public class RescueController {

    private final RescueService rescueService;

    /** POST /rescue — submit a new rescue request */
    @PostMapping
    public ResponseEntity<?> submit(@Valid @RequestBody RescueRequestDTO dto) {
        try {
            RescueRequest saved = rescueService.submit(dto);
            return ResponseEntity.ok(Map.of(
                "message", "Rescue request submitted successfully",
                "referenceNumber", saved.getReferenceNumber(),
                "id", saved.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /** GET /rescue — get all rescue requests (admin) */
    @GetMapping
    public ResponseEntity<List<RescueRequest>> getAll() {
        return ResponseEntity.ok(rescueService.getAll());
    }

    /** GET /rescue/track/{referenceNumber} — track a specific rescue */
    @GetMapping("/track/{referenceNumber}")
    public ResponseEntity<?> track(@PathVariable String referenceNumber) {
        try {
            return ResponseEntity.ok(rescueService.getByReference(referenceNumber));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
