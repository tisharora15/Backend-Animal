package com.rescuepaw.repository;
import com.rescuepaw.entity.RescueRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface RescueRequestRepository extends JpaRepository<RescueRequest, Long> {
    Optional<RescueRequest> findByReferenceNumber(String referenceNumber);
    List<RescueRequest> findByStatus(RescueRequest.Status status);
}
