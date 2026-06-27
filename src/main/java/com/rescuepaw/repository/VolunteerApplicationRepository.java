package com.rescuepaw.repository;
import com.rescuepaw.entity.VolunteerApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface VolunteerApplicationRepository extends JpaRepository<VolunteerApplication, Long> {
    List<VolunteerApplication> findByEmail(String email);
    List<VolunteerApplication> findByStatus(VolunteerApplication.Status status);
}
