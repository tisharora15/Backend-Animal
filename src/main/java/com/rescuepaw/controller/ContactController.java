package com.rescuepaw.controller;

import com.rescuepaw.dto.ContactMessageDTO;
import com.rescuepaw.entity.ContactMessage;
import com.rescuepaw.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ContactController
 * POST /contact     → ContactPage.jsx form submission
 * GET  /contact     → Admin: view all messages
 */
@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<?> send(@Valid @RequestBody ContactMessageDTO dto) {
        try {
            ContactMessage saved = contactService.send(dto);
            return ResponseEntity.ok(Map.of(
                "message", "Message sent! We'll respond within 24 hours.",
                "id", saved.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAll() {
        return ResponseEntity.ok(contactService.getAll());
    }
}
