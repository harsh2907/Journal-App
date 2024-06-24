package com.example.journalApp.services;

import com.example.journalApp.models.Journal;
import com.example.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public ObjectId createJournal(Journal journal) {
        return journalEntryRepository.save(journal).getId();
    }

    public List<Journal> getAllJournals() {
        return journalEntryRepository.findAll();
    }


    public void deleteJournal(String id) {
        journalEntryRepository.deleteById(id);
    }

    public Optional<Journal> getJournalById(String id) {
        return journalEntryRepository.findById(id);
    }
}
