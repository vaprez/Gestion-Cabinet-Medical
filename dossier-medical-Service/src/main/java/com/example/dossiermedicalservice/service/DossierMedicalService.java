package com.example.dossiermedicalservice.service;

import com.example.dossiermedicalservice.beans.Dossier_medical;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DossierMedicalService {

    // Liste statique d'exemples
    private List<Dossier_medical> records = Arrays.asList(
            new Dossier_medical(1L, 1L, 1L, "Résumé médical 1",
                    Arrays.asList("Prescription A", "Prescription B"),
                    Arrays.asList("Note 1", "Note 2"),
                    LocalDateTime.now()),
            new Dossier_medical(2L, 2L, 2L, "Résumé médical 2",
                    Collections.singletonList("Prescription C"),
                    Collections.singletonList("Note 3"),
                    LocalDateTime.now()),
            new Dossier_medical(3L, 3L, 3L, "Résumé médical 3",
                    Arrays.asList("Prescription D", "Prescription E", "Prescription F"),
                    Arrays.asList("Note 4", "Note 5", "Note 6"),
                    LocalDateTime.now())
    );

    public List<Dossier_medical> getAllDossiers() {
        return records;
    }
    public Dossier_medical getDossierById(Long id) {
        return records.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public String addDossier(Dossier_medical dossier) {
        records.add(dossier);
        return "Dossier ajouté avec succès !";
    }
    public String deleteDossier(Long id) {
        records.removeIf(d -> d.getId().equals(id));
        return "Dossier supprimé avec succès !";
    }
    public String updateDossier(Dossier_medical dossier) {
        int index = records.indexOf(dossier);
        records.set(index, dossier);
        return " Dossier mis à jour avec succès !";
    }

}
