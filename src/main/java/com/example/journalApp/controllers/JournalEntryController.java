package com.example.journalApp.controllers;

import com.example.journalApp.models.Journal;
import com.example.journalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<List<Journal>> getAllJournals(){
        List<Journal> journals = journalEntryService.getAllJournals();
        if(journals!=null && !journals.isEmpty()){
            return new ResponseEntity<>(journals, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody Journal journalEntry){
        try {
            journalEntry.setCreatedAt(LocalDateTime.now());
            ObjectId id = journalEntryService.createJournal(journalEntry);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{entryId}")
    public ResponseEntity<?> getJournalById(@PathVariable String entryId){

        Optional<Journal> journalOptional = journalEntryService.getJournalById(entryId);;
        if (journalOptional.isPresent()) {
            return new ResponseEntity<>(journalOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("No entry found with provided id", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("id/{entryId}")
    public ResponseEntity<?> updateJournalById(@PathVariable String entryId, @RequestBody Journal updatedJournal ){
        Optional<Journal> journalOptional = journalEntryService.getJournalById(entryId);

        if (journalOptional.isPresent() ) {
            Journal journal = journalOptional.get();

            journal.setTitle(updatedJournal.getTitle());
            journal.setContent(updatedJournal.getContent());
            journalEntryService.createJournal(journal);

            return new ResponseEntity<>(journal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Journal not found", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("id/{entryId}")
    public ResponseEntity<String> deleteJournalById(@PathVariable String entryId){
        boolean isPresent = journalEntryService.getJournalById(entryId).isPresent();

        if (isPresent) {
            journalEntryService.deleteJournal(entryId);
            return new ResponseEntity<>("Entry deleted successfully", HttpStatus.GONE);
        } else {
            return new ResponseEntity<>("No entry found with provided id", HttpStatus.BAD_REQUEST);
        }
    }
}

