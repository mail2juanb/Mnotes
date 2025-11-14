package com.microdiab.mnotes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Document(collection = "collection_notes")
public class Note {

    @Id
    private String id;      // Identifiant unique généré par MongoDB (ObjectId mappé en String)

    @NotNull(message = "patId cannot be null")
    @Positive(message = "patId must be a positive number")
    private Long patId;     // Clé de correspondance avec ta base SQL

    @NotBlank(message = "patient is mandatory")
    private String patient; // Nom du patient (optionnel, selon tes besoins)

    @NotBlank(message = "note is mandatory")
    private String note;    // Champ texte pour la note (supporte les retours à la ligne)

    // Constructeurs
    public Note() {
    }

    public Note(String id, Long patId, String patient, String note) {
        this.id = id;
        this.patId = patId;
        this.patient = patient;
        this.note = note;
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPatId() {
        return patId;
    }

    public void setPatId(Long patId) {
        this.patId = patId;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public String toString() {
        return "Note{" +
                "patId=" + patId +
                ", patient='" + patient + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
