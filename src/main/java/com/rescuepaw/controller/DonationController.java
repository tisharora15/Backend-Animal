package com.rescuepaw.controller;

import com.rescuepaw.dto.DonationDTO;
import com.rescuepaw.entity.Donation;
import com.rescuepaw.service.DonationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * DonationController
 * POST /donate    → DonatePage.jsx form submission
 * GET  /donate    → Admin: view all donations
 */
@RestController
@RequestMapping("/donate")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<?> donate(@Valid @RequestBody DonationDTO dto) {
        try {
            Donation saved = donationService.donate(dto);
            return ResponseEntity.ok(Map.of(
                "message", "Thank you for your generous donation!",
                "donationId", saved.getId(),
                "amount", saved.getAmount(),
                "type", saved.getDonationType()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Donation>> getAll() {
        return ResponseEntity.ok(donationService.getAll());
    }
}
