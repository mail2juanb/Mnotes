package com.microdiab.mnotes.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microdiab.mnotes.model.Note;
import com.microdiab.mnotes.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Component
public class JsonDataInitializer implements CommandLineRunner {

    private final NoteRepository noteRepository;
    private final ObjectMapper objectMapper;

    public JsonDataInitializer(NoteRepository noteRepository, ObjectMapper objectMapper) {
        this.noteRepository = noteRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        // Charge le fichier JSON depuis les ressources
        InputStream inputStream = new ClassPathResource("data/notes.json").getInputStream();

        // Convertit le JSON en objets Note
        Note[] notes = objectMapper.readValue(inputStream, Note[].class);

        // Récupère toutes les notes existantes
        List<Note> existingNotes = noteRepository.findAll();

        // Filtre les notes à ajouter (celles qui n'existent pas déjà selon patId, patient et note)
        List<Note> newNotes = Arrays.stream(notes)
                .filter(note ->
                        existingNotes.stream()
                                .noneMatch(existingNote ->
                                        existingNote.getPatId().equals(note.getPatId()) &&
                                                existingNote.getPatient().equals(note.getPatient()) &&
                                                existingNote.getNote().equals(note.getNote())
                                )
                )
                .toList();

        // Sauvegarde uniquement les nouvelles notes
        if (!newNotes.isEmpty()) {
            noteRepository.saveAll(newNotes);
            System.out.println("Nouvelles données JSON insérées dans MongoDB : " + newNotes.size() + " notes ajoutées.");
        } else {
            System.out.println("Aucune nouvelle note à ajouter.");
        }
    }
}
