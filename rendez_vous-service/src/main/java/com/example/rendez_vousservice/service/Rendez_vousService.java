package com.example.rendez_vousservice.service;

import com.example.rendez_vousservice.beans.Rendez_vous;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Rendez_vousService {

    //Liste des rendez_vous
    private static final List<Rendez_vous> rendezVousList = new ArrayList<>(Arrays.asList(
            new Rendez_vous(
                    1L,
                    "Consultation Cardiaque",
                    "2023-11-01T10:00",
                    "2023-11-01T11:00",
                    "Consultation avec le cardiologue",
                    "Clinique A, Salle 101",
                    1L,
                    "https://calendar.google.com/event?eid=consultation_cardiaque_1"
            ),
            new Rendez_vous(
                    2L,
                    "Suivi Respiratoire",
                    "2023-11-02T09:00",
                    "2023-11-02T09:30",
                    "Consultation avec le pneumologue",
                    "Clinique B, Salle 202",
                    2L,
                    "https://calendar.google.com/event?eid=suivi_respiratoire_2"
            ),
            new Rendez_vous(
                    3L,
                    "Contrôle Diabète",
                    "2023-11-03T14:00",
                    "2023-11-03T14:45",
                    "Contrôle des niveaux de sucre",
                    "Clinique C, Salle 303",
                    3L,
                    "https://calendar.google.com/event?eid=controle_diabete_3"
            ),
            new Rendez_vous(
                    4L,
                    "Réunion Médicale",
                    "2023-11-04T16:00",
                    "2023-11-04T17:30",
                    "Réunion de préparation de dossier",
                    "Hôpital D, Salle de conférence",
                    4L,
                    "https://calendar.google.com/event?eid=reunion_medicale_4"
            ),
            new Rendez_vous(
                    5L,
                    "Vaccination",
                    "2023-11-05T08:30",
                    "2023-11-05T09:00",
                    "Administration du vaccin saisonnier",
                    "Centre Médical E, Salle V1",
                    5L,
                    "https://calendar.google.com/event?eid=vaccination_5"
            )
    ));

    public List<Rendez_vous> getRendezVousList() {
        return rendezVousList;
    }
    public Rendez_vous getRendezVousById(Long id) {
        return rendezVousList.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    public String deleteRendezVous(Long id) {
        rendezVousList.removeIf(x -> x.getId().equals(id));
        return "Rendez_vous supprimé";
    }
    public String updateRendezVous(Long id,Rendez_vous rendezVous) {
        for (int i = 0; i < rendezVousList.size(); i++) {
            if(rendezVousList.get(i).getId().equals(id)) {
                rendezVousList.get(i).setId(rendezVous.getId());
                return "Rendez_vous modifié";
            }
        }
        return "Erreur lors de la modification";
    }


    @Retryable(
            value = { HttpClientErrorException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )

    public String generateGoogleCalendarLink(String eventTitle, String startDateTime, String endDateTime,
                                             String description, String location) {
        String startUtc = convertToUTC(startDateTime);
        String endUtc = convertToUTC(endDateTime);

        String calendarLink = "https://www.google.com/calendar/render?action=TEMPLATE" +
                "&text=" + URLEncoder.encode(eventTitle, StandardCharsets.UTF_8) +
                "&dates=" + startUtc + "/" + endUtc +
                "&details=" + URLEncoder.encode(description, StandardCharsets.UTF_8) +
                "&location=" + URLEncoder.encode(location, StandardCharsets.UTF_8) +
                "&sf=true";

        return calendarLink;
    }

    // Méthode de fallback
    public String fallbackForRemoteCall(String classname) {
        return "Résultat par défaut (fallback) en raison de l'échec de l'appel distant";
    }


    // Méthode fictive pour la conversion de la date en UTC
    private String convertToUTC(String localDateTime) {
        return localDateTime.replace(" ", "T") + "Z"; // Exemple simple de conversion
    }

    public String addRendezVous(Rendez_vous rendezVous) {
        try {
            rendezVousList.add(rendezVous); // Ajouter le rendez-vous à la liste locale
            // Générer le lien Google Calendar
            String googleCalendarLink = generateGoogleCalendarLink(
                    "Rendez-vous: " + rendezVous.getDescription(),
                    rendezVous.getStartDateTime(), // Utilisation directe
                    rendezVous.getEndDateTime(),  // Utilisation directe
                    "Consultation avec le docteur ID : " + rendezVous.getPraticien_Id(),
                    "Lieu de la consultation"
            );
            // Ajouter le lien au rendez-vous ou le retourner
            rendezVous.setGoogleCalendarLink(googleCalendarLink);
            System.out.println("Lien Google Calendar : " + googleCalendarLink);
            return "Rendez-vous ajouté et lien Google Calendar généré : " + googleCalendarLink;
        } catch (Exception e) {
            e.printStackTrace();
            return "échec de la génération du lien Google Calendar.";
        }
    }

}
