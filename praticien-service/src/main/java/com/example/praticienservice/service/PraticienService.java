package com.example.praticienservice.service;

import com.example.praticienservice.beans.Consultation;
import com.example.praticienservice.beans.Praticien;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PraticienService {

    // Liste des docteurs
    private List<Praticien> praticiens = new ArrayList<>(Arrays.asList(
            new Praticien(1L, "Dr. Smith", "Cardiology"),
            new Praticien(2L, "Dr. Brown", "Pulmonology"),
            new Praticien(3L, "Dr. Taylor", "Endocrinology")
    ));

    // Liste des consultations
    private List<Consultation> consultations = new ArrayList<>(Arrays.asList(
            new Consultation(1L,1L, 1L, "2024-01-10", "Routine Checkup", "Completed"),
            new Consultation(2L,2L, 2L, "2024-02-15", "Flu Symptoms", "Pending"),
            new Consultation(3L,3L, 3L, "2024-03-05", "Back Pain", "In Progress")
    ));

    public List<Praticien> getPraticiens() {
        return praticiens;
    }

    public Praticien getPraticien(Long id) {
        for (Praticien praticien : praticiens) {
            if (praticien.getId().equals(id)) {
                return praticien;
            }
        }
        return null;
    }
    public String addPraticien(Praticien praticien) {
        praticiens.add(praticien);
        return "Praticien added";
    }
    public String updatePraticien(Praticien praticien) {
        int index = praticiens.indexOf(praticien);
        praticiens.set(index, praticien);
        return "Praticien updated";
    }
    public String deletePraticien(Long id) {
        int index = praticiens.indexOf(id);
        praticiens.remove(index);
        return "Praticien deleted";
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }
    public Consultation getConsultation(Long id) {
        return consultations.stream()
                .filter(consultation -> consultation.getId().equals(id))
                .findFirst()
                .orElse(null); // Retourne null si aucune consultation avec cet id n'est trouvée
    }
    public String addConsultation(Consultation consultation) {
        consultations.add(consultation);
        return "Consultation added";
    }
    public String updateConsultation(Consultation consultation) {
        int index = consultations.indexOf(consultation);
        consultations.set(index, consultation);
        return "Consultation updated";
    }




}
