package com.microdiab.mnotes.repository;

import com.microdiab.mnotes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    // Trouve toutes les notes pour un patId donné
    List<Note> findByPatId(Long patId);

    // Trouve une note par son id MongoDB
    Optional<Note> findById(String id);

    // Supprime toutes les notes pour un patId donné
    void deleteByPatId(Long patId);

    // Vérifie si une note existe pour un patId donné
    boolean existsByPatId(Long patId);

}
