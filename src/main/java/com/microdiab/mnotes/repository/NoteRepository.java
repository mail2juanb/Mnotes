package com.microdiab.mnotes.repository;

import com.microdiab.mnotes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    // Trouve toutes les notes pour un patId donné
    List<Note> findByPatId(Long patId);

}
