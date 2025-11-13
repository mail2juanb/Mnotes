package com.microdiab.mnotes.service;

import com.microdiab.mnotes.model.Note;
import com.microdiab.mnotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // Sauvegarde une note
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    // Récupère toutes les notes pour un patId
    public List<Note> getNotesByPatId(Long patId) {
        return noteRepository.findByPatId(patId);
    }

    // Récupère une note par son id MongoDB
    public Optional<Note> getNoteById(String id) {
        return noteRepository.findById(id);
    }

    // Supprime une note par son id
    public void deleteNote(String id) {
        noteRepository.deleteById(id);
    }

    // Supprime toutes les notes pour un patId
    public void deleteNotesByPatId(Long patId) {
        noteRepository.deleteByPatId(patId);
    }

    // Vérifie si des notes existent pour un patId
    public boolean notesExistForPatId(Long patId) {
        return noteRepository.existsByPatId(patId);
    }
}
