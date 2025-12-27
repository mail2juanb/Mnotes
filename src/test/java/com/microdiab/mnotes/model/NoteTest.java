package com.microdiab.mnotes.model;


import com.microdiab.mnotes.repository.NoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoteTest {

    @Autowired
    private LocalValidatorFactoryBean validator;


    @Test
    void testValidNote() {
        Note note = new Note("123", 1L, "Jean Dupont", "Note valide");
        var violations = validator.validate(note);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidPatId() {
        Note note = new Note("123", -1L, "Jean Dupont", "Note valide");
        var violations = validator.validate(note);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testInvalidPatient() {
        Note note = new Note("123", 1L, "", "Note valide");
        var violations = validator.validate(note);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testInvalidNote() {
        Note note = new Note("123", 1L, "Jean Dupont", "");
        var violations = validator.validate(note);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testGetIdAndSetId() {
        Note note = new Note();
        // Test avec un ID valide
        note.setId("test-id-123");
        assertEquals("test-id-123", note.getId());

        // Test avec un ID null
        note.setId(null);
        assertNull(note.getId());

        // Test avec un ID vide
        note.setId("");
        assertEquals("", note.getId());
    }

    @Test
    void testToString_WithAllFieldsSet() {
        Note note = new Note("123", 1L, "Jean Dupont", "Note de test");
        String expected = "Note{patId=1, patient='Jean Dupont', note='Note de test'}";
        assertEquals(expected, note.toString());
    }

    @Test
    void testToString_WithNullFields() {
        Note note = new Note();
        note.setPatId(null);
        note.setPatient(null);
        note.setNote(null);
        // Le toString() ne doit pas lancer d'exception même si certains champs sont null
        String result = note.toString();
        assertTrue(result.contains("patId=null"));
        assertTrue(result.contains("patient='null'"));
        assertTrue(result.contains("note='null'"));
    }

    @Test
    void testToString_WithEmptyFields() {
        Note note = new Note();
        note.setPatId(0L);
        note.setPatient("");
        note.setNote("");
        String result = note.toString();
        assertTrue(result.contains("patId=0"));
        assertTrue(result.contains("patient=''"));
        assertTrue(result.contains("note=''"));
    }
}
