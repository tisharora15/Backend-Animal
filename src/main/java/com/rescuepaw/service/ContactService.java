package com.rescuepaw.service;

import com.rescuepaw.dto.ContactMessageDTO;
import com.rescuepaw.entity.ContactMessage;
import com.rescuepaw.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactMessageRepository contactRepo;

    public ContactMessage send(ContactMessageDTO dto) {
        ContactMessage msg = ContactMessage.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .phone(dto.getPhone())
            .subject(dto.getSubject())
            .message(dto.getMessage())
            .replied(false)
            .build();
        return contactRepo.save(msg);
    }

    public List<ContactMessage> getAll() { return contactRepo.findAll(); }
    public List<ContactMessage> getUnreplied() { return contactRepo.findByReplied(false); }
}
