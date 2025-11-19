package com.microdiab.mnotes.controller;

import com.microdiab.mnotes.model.Note;
import com.microdiab.mnotes.service.NoteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    // Crée une nouvelle note pour un patId
    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        logger.info("NOTE CONTROLLER - createNote - start...");
        logger.info("NOTE CONTROLLER - note.patId = {} // note.patient = {} // note.note = {}", note.getPatId(), note.getPatient(), note.getPatId());
        Note savedNote = noteService.saveNote(note);
        logger.info("NOTE CONTROLLER - createNote - end...");
        return ResponseEntity.ok(savedNote);
    }

    // Récupère toutes les notes pour un patId
    @GetMapping("/notes/{patId}")
    public ResponseEntity<List<Note>> getNotesByPatId(@PathVariable Long patId) {
        List<Note> notes = noteService.getNotesByPatId(patId);
        return ResponseEntity.ok(notes);
    }

    // Récupère une note par son id
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        Optional<Note> note = noteService.getNoteById(id);
        return note.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprime une note par son id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    // Supprime toutes les notes pour un patId
    @DeleteMapping("/patient/{patId}")
    public ResponseEntity<Void> deleteNotesByPatId(@PathVariable Long patId) {
        noteService.deleteNotesByPatId(patId);
        return ResponseEntity.noContent().build();
    }
}
