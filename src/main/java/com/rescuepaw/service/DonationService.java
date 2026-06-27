package com.rescuepaw.service;

import com.rescuepaw.dto.DonationDTO;
import com.rescuepaw.entity.Donation;
import com.rescuepaw.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepo;

    public Donation donate(DonationDTO dto) {
        Donation.DonationType type = dto.getDonationType().equalsIgnoreCase("monthly")
            ? Donation.DonationType.MONTHLY
            : Donation.DonationType.ONE_TIME;

        Donation donation = Donation.builder()
            .donorEmail(dto.getDonorEmail())
            .donorName(dto.getDonorName())
            .amount(dto.getAmount())
            .donationType(type)
            .status(Donation.Status.COMPLETED)
            .build();

        return donationRepo.save(donation);
    }

    public List<Donation> getAll() { return donationRepo.findAll(); }
}
