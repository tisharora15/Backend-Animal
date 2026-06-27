package com.rescuepaw.controller;

import com.rescuepaw.dto.AdoptionApplicationDTO;
import com.rescuepaw.entity.AdoptionApplication;
import com.rescuepaw.service.AdoptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * AdoptionController — handles adoption applications
 *
 * POST /adoption/apply          → AnimalDetailPage.jsx submits form
 * GET  /adoption                → Admin: view all applications
 * GET  /adoption/animal/{id}    → Admin: applications for a specific animal
 * PATCH /adoption/{id}/status   → Admin: approve or reject
 */
@RestController
@RequestMapping("/adoption")
@RequiredArgsConstructor
public class AdoptionController {

    private final AdoptionService adoptionService;

    /** POST /adoption/apply */
    @PostMapping("/apply")
    public ResponseEntity<?> apply(@Valid @RequestBody AdoptionApplicationDTO dto) {
        try {
            AdoptionApplication saved = adoptionService.apply(dto);
            return ResponseEntity.ok(Map.of(
                "message", "Adoption application submitted! We'll be in touch within 48 hours.",
                "applicationId", saved.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /** GET /adoption — all applications (admin) */
    @GetMapping
    public ResponseEntity<List<AdoptionApplication>> getAll() {
        return ResponseEntity.ok(adoptionService.getAll());
    }

    /** GET /adoption/animal/{animalId} — applications for one animal (admin) */
    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<AdoptionApplication>> getByAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(adoptionService.getByAnimalId(animalId));
    }

    /** PATCH /adoption/{id}/status?status=APPROVED — update application status */
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
        @PathVariable Long id,
        @RequestParam String status
    ) {
        try {
            return ResponseEntity.ok(adoptionService.updateStatus(id, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
