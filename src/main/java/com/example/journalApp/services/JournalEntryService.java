package com.example.journalApp.services;

import com.example.journalApp.models.Journal;
import com.example.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public String createJournal(Journal journal){
        return journalEntryRepository.save(journal).getId();
    }

    public List<Journal> getAllJournals(){
        return journalEntryRepository.findAll();
    }

    public String updateJournal(String id,Journal updatedJournal){
       Optional<Journal> journalOptional = journalEntryRepository.findById(id);

        if(journalOptional.isPresent()){
            Journal journal = journalOptional.get();
            journal.setTitle(updatedJournal.getTitle());
            journal.setContent(updatedJournal.getContent());
            journalEntryRepository.save(journal);
            return "Journal updated successfully";
        } else {
            return "Journal not found";
        }
    }

    public String deleteJournal(String id){
        boolean isPresent = journalEntryRepository.findById(id).isPresent();

        if(isPresent){
            journalEntryRepository.deleteById(id);
            return "Entry deleted successfully";
        }else{
            return "No entry found with provided id";
        }
    }

    public Journal getJournalById(String id){
        Optional<Journal> journalOptional = journalEntryRepository.findById(id);

        return journalOptional.orElse(null);

    }
}
