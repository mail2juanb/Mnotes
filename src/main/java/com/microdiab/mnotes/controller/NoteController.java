package com.microdiab.mnotes.controller;

import com.microdiab.mnotes.model.Note;
import com.microdiab.mnotes.service.NoteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> createNote(@Valid @RequestBody Note note, BindingResult result) {
        // ResponseEntity<?> : Permet de retourner soit le patient sauvegardé, soit une erreur.
        logger.info("NOTE CONTROLLER - note.patId = {} // note.patient = {} // note.note = {}", note.getPatId(), note.getPatient(), note.getPatId());

        if (result.hasErrors()) {
            // Retourne les erreurs de validation
            logger.info("Note non sauvegardé, erreur de validation : {}", result.getAllErrors());
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Note savedNote = noteService.saveNote(note);
        return ResponseEntity.ok(savedNote);
    }

    // Récupère toutes les notes pour un patId
    @GetMapping("/notes/{patId}")
    public ResponseEntity<List<Note>> getNotesByPatId(@PathVariable Long patId) {
        List<Note> notes = noteService.getNotesByPatId(patId);
        return ResponseEntity.ok(notes);
    }
}
