package com.rescuepaw.service;

import com.rescuepaw.dto.AdoptionApplicationDTO;
import com.rescuepaw.entity.AdoptionApplication;
import com.rescuepaw.repository.AdoptionApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionService {

    private final AdoptionApplicationRepository adoptionRepo;

    public AdoptionApplication apply(AdoptionApplicationDTO dto) {
        AdoptionApplication app = AdoptionApplication.builder()
            .animalId(dto.getAnimalId())
            .animalName(dto.getAnimalName())
            .name(dto.getName())
            .email(dto.getEmail())
            .phone(dto.getPhone())
            .address(dto.getAddress())
            .homeType(dto.getHomeType())
            .hasYard(dto.getHasYard())
            .hasPets(dto.getHasPets())
            .experience(dto.getExperience())
            .reason(dto.getReason())
            .status(AdoptionApplication.Status.PENDING)
            .build();
        return adoptionRepo.save(app);
    }

    public List<AdoptionApplication> getAll() { return adoptionRepo.findAll(); }
    public List<AdoptionApplication> getByEmail(String email) { return adoptionRepo.findByEmail(email); }
    public List<AdoptionApplication> getByAnimalId(Long id) { return adoptionRepo.findByAnimalId(id); }

    public AdoptionApplication updateStatus(Long id, String status) {
        AdoptionApplication app = adoptionRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Application not found"));
        app.setStatus(AdoptionApplication.Status.valueOf(status.toUpperCase()));
        return adoptionRepo.save(app);
    }
}
