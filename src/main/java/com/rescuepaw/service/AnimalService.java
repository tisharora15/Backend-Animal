package com.rescuepaw.service;

import com.rescuepaw.entity.Animal;
import com.rescuepaw.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    /** Returns all available animals — used by AdoptPage.jsx */
    public List<Animal> getAllAvailable() {
        return animalRepository.findByStatus(Animal.Status.AVAILABLE);
    }

    /** Returns all animals regardless of status (admin use) */
    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    /** Returns a single animal by ID — used by AnimalDetailPage.jsx */
    public Animal getById(Long id) {
        return animalRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Animal not found with id: " + id));
    }

    /** Filter by type: "Dog" or "Cat" */
    public List<Animal> getByType(String type) {
        return animalRepository.findByStatusAndType(Animal.Status.AVAILABLE, type);
    }

    /** Add a new animal (admin) */
    public Animal create(Animal animal) {
        return animalRepository.save(animal);
    }

    /** Update animal details (admin) */
    public Animal update(Long id, Animal updated) {
        Animal existing = getById(id);
        existing.setName(updated.getName());
        existing.setType(updated.getType());
        existing.setBreed(updated.getBreed());
        existing.setAge(updated.getAge());
        existing.setGender(updated.getGender());
        existing.setLocation(updated.getLocation());
        existing.setImage(updated.getImage());
        existing.setDescription(updated.getDescription());
        existing.setFullDescription(updated.getFullDescription());
        existing.setPersonality(updated.getPersonality());
        existing.setStatus(updated.getStatus());
        existing.setAdoptionFee(updated.getAdoptionFee());
        return animalRepository.save(existing);
    }

    /** Mark animal as adopted */
    public Animal markAdopted(Long id) {
        Animal animal = getById(id);
        animal.setStatus(Animal.Status.ADOPTED);
        return animalRepository.save(animal);
    }

    /** Delete an animal (admin) */
    public void delete(Long id) {
        animalRepository.deleteById(id);
    }
}
