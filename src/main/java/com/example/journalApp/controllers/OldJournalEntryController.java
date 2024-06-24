package com.example.journalApp.controllers;

import com.example.journalApp.models.Journal;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("journal/v1")
public class OldJournalEntryController {

    public Map<ObjectId, Journal> journals = new HashMap<>();

    @GetMapping
    public List<Journal> getAllJournals() {
        return new ArrayList<>(journals.values());
    }

    @PostMapping
    public String createEntry(@RequestBody Journal journalEntry) {
        journals.put(journalEntry.getId(), journalEntry);
        return "Entry created successfully";
    }

    @GetMapping("id/{entryId}")
    public Journal getJournalById(@PathVariable ObjectId entryId) {
        return journals.get(entryId);
    }

    @PutMapping("id/{entryId}")
    public Journal updateJournalById(@PathVariable ObjectId entryId, @RequestBody Journal journalEntry) {
        return journals.put(entryId, journalEntry);
    }

    @DeleteMapping("id/{entryId}")
    public Journal deleteJournalById(@PathVariable String entryId) {
        return journals.remove(entryId);
    }
}
