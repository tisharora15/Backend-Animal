package com.rescuepaw.repository;
import com.rescuepaw.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonorEmail(String email);
    List<Donation> findByDonationType(Donation.DonationType type);
}
