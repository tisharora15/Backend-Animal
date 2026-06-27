package com.rescuepaw.repository;
import com.rescuepaw.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByStatus(Animal.Status status);
    List<Animal> findByType(String type);
    List<Animal> findByStatusAndType(Animal.Status status, String type);
}
