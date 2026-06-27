package com.rescuepaw.controller;

import com.rescuepaw.entity.Animal;
import com.rescuepaw.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * AnimalController — handles all /animals endpoints
 *
 * GET  /animals          → AdoptPage.jsx  (fetches all animals)
 * GET  /animals/{id}     → AnimalDetailPage.jsx (single animal)
 * GET  /animals?type=Dog → AdoptPage.jsx filter
 * POST /animals          → Admin: add new animal
 * PUT  /animals/{id}     → Admin: update animal
 * DELETE /animals/{id}   → Admin: remove animal
 */
@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    /** GET /animals  — returns all AVAILABLE animals */
    @GetMapping
    public ResponseEntity<List<Animal>> getAll(
        @RequestParam(required = false) String type
    ) {
        if (type != null && !type.isBlank()) {
            return ResponseEntity.ok(animalService.getByType(type));
        }
        return ResponseEntity.ok(animalService.getAllAvailable());
    }

    /** GET /animals/{id}  — returns one animal by ID */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(animalService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** POST /animals  — create a new animal (admin) */
    @PostMapping
    public ResponseEntity<Animal> create(@RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.create(animal));
    }

    /** PUT /animals/{id}  — update an animal (admin) */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Animal animal) {
        try {
            return ResponseEntity.ok(animalService.update(id, animal));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** PATCH /animals/{id}/adopt  — mark animal as adopted */
    @PatchMapping("/{id}/adopt")
    public ResponseEntity<?> markAdopted(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(animalService.markAdopted(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** DELETE /animals/{id}  — delete an animal (admin) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        animalService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Animal deleted successfully"));
    }
}
