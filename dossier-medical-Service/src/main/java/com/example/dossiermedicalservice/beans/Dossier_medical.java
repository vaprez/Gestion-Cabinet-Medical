package com.example.dossiermedicalservice.beans;

import java.time.LocalDateTime;
import java.util.List;

public class Dossier_medical {
    private Long id;
    private Long patientId; // Référence au service Patient
    private Long doctorId; // Référence au service Praticien
    private String summary; // Résumé médical
    private List<String> prescriptions; // Liste des prescriptions
    private List<String> notes; // Notes du praticien
    private LocalDateTime lastUpdated; // Date de mise à jour

    public Dossier_medical() {
    }

    public Dossier_medical(Long id, Long patientId, Long doctorId, String summary, List<String> prescriptions, List<String> notes, LocalDateTime lastUpdated) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.summary = summary;
        this.prescriptions = prescriptions;
        this.notes = notes;
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPatientId() {
        return patientId;
    }
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    public Long getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public List<String> getPrescriptions() {
        return prescriptions;
    }
    public void setPrescriptions(List<String> prescriptions) {
        this.prescriptions = prescriptions;
    }
    public List<String> getNotes() {
        return notes;
    }
    public void setNotes(List<String> notes) {
        this.notes = notes;
    }
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
