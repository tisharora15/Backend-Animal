package com.rescuepaw.service;

import com.rescuepaw.dto.VolunteerApplicationDTO;
import com.rescuepaw.entity.VolunteerApplication;
import com.rescuepaw.repository.VolunteerApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerService {

    private final VolunteerApplicationRepository volunteerRepo;

    public VolunteerApplication apply(VolunteerApplicationDTO dto) {
        // Convert interests list to comma-separated string for storage
        String interests = dto.getInterests() == null ? "" :
            String.join(",", dto.getInterests());

        VolunteerApplication app = VolunteerApplication.builder()
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .email(dto.getEmail())
            .phone(dto.getPhone())
            .interests(interests)
            .availability(dto.getAvailability())
            .experience(dto.getExperience())
            .message(dto.getMessage())
            .status(VolunteerApplication.Status.PENDING)
            .build();

        return volunteerRepo.save(app);
    }

    public List<VolunteerApplication> getAll() { return volunteerRepo.findAll(); }
}
