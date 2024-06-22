package com.example.journalApp.controllers;

import com.example.journalApp.models.Journal;
import com.example.journalApp.services.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<Journal> getAllJournals(){
        return journalEntryService.getAllJournals();
    }

    @PostMapping
    public String createEntry(@RequestBody Journal journalEntry){
        return journalEntryService.createJournal(journalEntry);
    }

    @GetMapping("id/{entryId}")
    public Journal getJournalById(@PathVariable String entryId){
        return journalEntryService.getJournalById(entryId);
    }

    @PutMapping("id/{entryId}")
    public String updateJournalById(@PathVariable String entryId,@RequestBody Journal journalEntry ){
        return journalEntryService.updateJournal(entryId,journalEntry);
    }

    @DeleteMapping("id/{entryId}")
    public String deleteJournalById(@PathVariable String entryId){
        return journalEntryService.deleteJournal(entryId);
    }
}

