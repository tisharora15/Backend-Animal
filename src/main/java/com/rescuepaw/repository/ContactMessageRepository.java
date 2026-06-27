package com.rescuepaw.repository;
import com.rescuepaw.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    List<ContactMessage> findByReplied(Boolean replied);
}
