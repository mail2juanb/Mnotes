package com.microdiab.mnotes.service;

import com.microdiab.mnotes.model.Note;
import com.microdiab.mnotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
