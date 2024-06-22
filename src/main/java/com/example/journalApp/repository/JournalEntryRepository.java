package com.example.journalApp.repository;

import com.example.journalApp.models.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends MongoRepository<Journal,String> {
}
