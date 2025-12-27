package com.microdiab.mnotes.controller;

import com.microdiab.mnotes.model.Note;
import com.microdiab.mnotes.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteControllerTest {

    @Mock
    private NoteService noteService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private NoteController noteController;

    private Note note;

    @BeforeEach
    void setUp() {
        note = new Note();
        note.setPatId(1L);
        note.setPatient("Test Patient");
        note.setNote("Test Note");
    }

    // Test pour la création d'une note valide
    @Test
    void createNote_ValidNote_ReturnsSavedNote() {
        // Arrange
        when(bindingResult.hasErrors()).thenReturn(false);
        when(noteService.saveNote(any(Note.class))).thenReturn(note);

        // Act
        ResponseEntity<?> response = noteController.createNote(note, bindingResult);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(note, response.getBody());
    }

    // Test pour la création d'une note invalide
    @Test
    void createNote_InvalidNote_ReturnsValidationErrors() {
        // Arrange
        when(bindingResult.hasErrors()).thenReturn(true);
        ObjectError error = new ObjectError("note", "Erreur de validation");
        when(bindingResult.getAllErrors()).thenReturn(List.of(error));

        // Act
        ResponseEntity<?> response = noteController.createNote(note, bindingResult);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // Test pour récupérer les notes par patId
    @Test
    void getNotesByPatId_ReturnsListOfNotes() {
        // Arrange
        Long patId = 1L;
        List<Note> notes = List.of(note); // Utilisation de List.of() pour une liste immutable
        when(noteService.getNotesByPatId(patId)).thenReturn(notes);

        // Act
        ResponseEntity<List<Note>> response = noteController.getNotesByPatId(patId);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notes, response.getBody());
    }
}