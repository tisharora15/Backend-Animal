package com.rescuepaw.service;

import com.rescuepaw.dto.RescueRequestDTO;
import com.rescuepaw.entity.RescueRequest;
import com.rescuepaw.repository.RescueRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RescueService {

    private final RescueRequestRepository rescueRequestRepository;

    /** Submit a new rescue request — generates a reference number like "RP-89621624" */
    public RescueRequest submit(RescueRequestDTO dto) {

        String refNumber = "RP-" + System.currentTimeMillis() % 100_000_000;

        RescueRequest.Urgency urgency = RescueRequest.Urgency.MEDIUM;
        if (dto.getUrgency() != null) {
            try { urgency = RescueRequest.Urgency.valueOf(dto.getUrgency().toUpperCase()); }
            catch (IllegalArgumentException ignored) {}
        }

        RescueRequest request = RescueRequest.builder()
            .reporterName(dto.getReporterName())
            .phone(dto.getPhone())
            .email(dto.getEmail())
            .animalType(dto.getAnimalType())
            .location(dto.getLocation())
            .description(dto.getDescription())
            .urgency(urgency)
            .referenceNumber(refNumber)
            .status(RescueRequest.Status.PENDING)
            .build();

        return rescueRequestRepository.save(request);
    }

    public List<RescueRequest> getAll() { return rescueRequestRepository.findAll(); }

    public RescueRequest getByReference(String ref) {
        return rescueRequestRepository.findByReferenceNumber(ref)
            .orElseThrow(() -> new RuntimeException("Rescue request not found"));
    }
}
