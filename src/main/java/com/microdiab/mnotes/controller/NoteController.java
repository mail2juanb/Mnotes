package com.microdiab.mnotes.controller;

import com.microdiab.mnotes.model.Note;
import com.microdiab.mnotes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    // Crée une nouvelle note
    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        Note savedNote = noteService.saveNote(note);
        return ResponseEntity.ok(savedNote);
    }

    // Récupère toutes les notes pour un patId
    @GetMapping("/patient/{patId}")
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
