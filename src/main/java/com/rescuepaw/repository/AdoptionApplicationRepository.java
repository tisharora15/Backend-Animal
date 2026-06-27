package com.rescuepaw.repository;
import com.rescuepaw.entity.AdoptionApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AdoptionApplicationRepository extends JpaRepository<AdoptionApplication, Long> {
    List<AdoptionApplication> findByEmail(String email);
    List<AdoptionApplication> findByAnimalId(Long animalId);
    List<AdoptionApplication> findByStatus(AdoptionApplication.Status status);
}
