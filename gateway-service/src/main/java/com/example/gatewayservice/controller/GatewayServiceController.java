package com.example.gatewayservice.controller;

import com.example.gatewayservice.beans.Dossier_medical;
import com.example.gatewayservice.beans.Patient;
import com.example.gatewayservice.beans.Praticien;
import com.example.gatewayservice.beans.Rendez_vous;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Api(value = "Controller Api gateway", description = "Controller pour centraliser toutes les routes" )
public class GatewayServiceController {

    @Lazy
    @Autowired
    RestTemplate restTemplate;

    // Méthode de fallback
    public String fallbackForRemoteCall(String classname) {
        return "Erreur lors de l'appel de la fonction veuillez ressayer plus tard";
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value ="/api/patients", method = RequestMethod.GET)
    @ApiOperation(value = "Liste de tous les patients", response = String.class, tags = "AllPatients")
    public String getPatients() {
        System.out.print("Recuperation des patients");

        String response = restTemplate.exchange("http://patient-service/api/patients",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        return "Liste des patients : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/praticien", method = RequestMethod.GET)
    @ApiOperation(value = "Liste de tous les praticiens", response = String.class, tags = "AllPraticiens")
    public String getPraticiens() {
        System.out.print("Recuperation des praticiens");

        String response = restTemplate.exchange("http://praticien-service/api/praticien",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        return "Liste des praticiens : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/dossiermedical",method = RequestMethod.GET)
    @ApiOperation(value = "Liste de tous les dossiers", response = String.class, tags = "AllDossiers")
    public String getDossierMedical() {
        System.out.print("Récuperations des dossiers");

        String response = restTemplate.exchange("http://dossiermedical-service/api/dossiermedical",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        return "Liste des dossiers : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "api/rendezvous", method = RequestMethod.GET)
    @ApiOperation(value = "Liste de tous les rendez-vous", response = String.class, tags = "AllRendezVous")
    public String getRendezVous() {
        System.out.print("Recuperation des rendez-vous");
        String response = restTemplate.exchange("http://rendezvous-service/api/rendezvous",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();

        return "Liste des rendez-vous : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value ="/api/patients/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Informations sur un patient", response = String.class, tags = "Patient")
    public String getPatientById(@PathVariable int id) {
        System.out.print("Recuperation des informations pour le patient" + id);

        String response = restTemplate.exchange("http://patient-service/api/patients/{id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, id).getBody();

        return "Informations détaillés du patient" + id + " : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value ="/api/praticien/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Informations sur un praticien", response = String.class, tags = "Praticien")
    public String getPraticienById(@PathVariable int id) {
        System.out.print("Recuperation des informations pour le praticien" + id);

        String response = restTemplate.exchange("http://praticien-service/api/praticiens/{id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, id).getBody();

        return "Informations détaillés du praticien" + id + " : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/dossiermedical/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Informations d'un dossier medical", response = String.class, tags = "DossierMedical")
    public String getDossierMedicalById(@PathVariable int id) {
        System.out.print("Recuperation des informations pour le dossier" + id);
        String response = restTemplate.exchange("http://dossiermedical-service/api/dossiermedical/{id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, id).getBody();
        return "Informations détaillés du dossier" + id + " : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/rendezvous/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Informations d'un rendez-vous", response = String.class, tags = "RendezVous")
    public String getRendezVousById(@PathVariable int id) {
        System.out.print("Recuperation des informations pour le rendez-vous" + id);
        String response = restTemplate.exchange("http://rendezvous-service/api/rendezvous/{id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, id).getBody();
        return "Informations détaillés du rendez-vous" + id + " :" + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/patients/add", method = RequestMethod.POST)
    @ApiOperation(value = "Ajout d'un nouveau patient", response = String.class, tags = "AddPatient")
    public String addPatient(@RequestBody Patient patient) {
        System.out.print("Ajout du patient ");
        // Effectuer l'appel au service patient via RestTemplate
        String response = restTemplate.postForObject(
                "http://patient-service/api/patients/add",
                patient,
                String.class
        );

        return "Réponse du service patient : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/praticien/add", method = RequestMethod.POST)
    @ApiOperation(value = "Ajout d'un nouveau praticien", response = String.class, tags = "AddPraticien")
    public String addPraticien(@RequestBody Praticien praticien) {
        System.out.print("Ajout d'un nouveau praticien : " + praticien.getName());

        String response = restTemplate.postForObject(
                "http://praticien-service/api/praticiens/add",
                praticien,
                String.class
        );

        return "Réponse du service Praticien : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/dossiermedical/add", method = RequestMethod.POST)
    @ApiOperation(value = "Creation d'un dossier medical", response = String.class, tags = "AddDossierMedical")
    public String addDossierMedical(@RequestBody Dossier_medical dossierMedical) {
        System.out.print("Ajout d'un nouveau dossier médical pour le patient : " + dossierMedical.getPatientId());

        String response = restTemplate.postForObject(
                "http://dossiermedical-service/api/dossiermedical/add",
                dossierMedical,
                String.class
        );

        return "Réponse du service Dossier Médical : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/rendezvous/add", method = RequestMethod.POST)
    @ApiOperation(value = "Ajout d'un nouveau rendez-vous", response = String.class, tags = "AddRendezVous")
    public String addRendezVous(@RequestBody Rendez_vous rendezVous) {
        System.out.print("Ajout d'un nouveau rendez-vous pour le patient : " + rendezVous.getId());

        String response = restTemplate.postForObject(
                "http://rendezvous-service/api/rendezvous/add",
                rendezVous,
                String.class
        );

        return "Réponse du service RendezVous : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/patients/update/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Modification des informations d'un patient", response = String.class, tags = "UpdatePatient")
    public String updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        System.out.print("Modification des informations du patient avec l'ID : " + id);
        // Utilisation de l'URL correcte du service distant (`api/patients/update/{id}`)
        String serviceUrl = "http://patient-service/api/patients/update/" + id;

        try {
            // Utilisation de RestTemplate pour effectuer une requête PUT
            restTemplate.put(serviceUrl, patient);

            return "Les informations du patient avec l'ID " + id + " ont été mises à jour avec succès.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la mise à jour des informations du patient avec l'ID " + id;
        }
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/praticien/update/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Modification des informations d'un praticien", response = String.class, tags = "UpdatePraticien")
    public String updatePraticien(@PathVariable Long id, @RequestBody Praticien praticien) {
        System.out.print("Modification des informations du praticien avec l'ID : " + id);

        String serviceUrl = "http://praticien-service/api/praticiens/update/" + id;

        try {
            restTemplate.put(serviceUrl, praticien);
            return "Les informations du praticien avec l'ID " + id + " ont été mises à jour avec succès.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la mise à jour des informations du praticien avec l'ID " + id;
        }
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/dossiermedical/update{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Modification d'un dossier medical", response = String.class, tags = "UpdateDossierMedical")
    public String updateDossierMedical(@PathVariable Long id, @RequestBody Dossier_medical dossierMedical) {
        System.out.print("Modification des informations du dossier médical avec l'ID : " + id);

        String serviceUrl = "http://dossiermedical-service/api/dossiermedical/update/" + id;

        try {
            restTemplate.put(serviceUrl, dossierMedical);
            return "Le dossier médical avec l'ID " + id + " a été mis à jour avec succès.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la mise à jour du dossier médical avec l'ID " + id;
        }
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/rendezvous/update/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Modification d'un rendez-vous", response = String.class, tags = "UpdateRendezVous")
    public String updateRendezVous(@PathVariable Long id, @RequestBody Rendez_vous rendezVous) {
        System.out.print("Modification des informations du rendez-vous avec l'ID : " + id);

        String serviceUrl = "http://rendezvous-service/api/rendezvous/update/" + id;

        try {
            restTemplate.put(serviceUrl, rendezVous);
            return "Le rendez-vous avec l'ID " + id + " a été mis à jour avec succès.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la mise à jour du rendez-vous avec l'ID " + id;
        }
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/patients/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Suppression d'un patient", response = String.class, tags = "DeletePatient")
    public String deletePatient(@PathVariable Long id) {
        System.out.print("Suppression du patient avec l'ID : " + id);

        String serviceUrl = "http://patient-service/api/patients/delete/" + id;

        try {
            restTemplate.delete(serviceUrl);
            return "Les informations du patient avec l'ID " + id + " ont été supprimés" ;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la suppression du patient avec l'ID " + id;
        }
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/praticien/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Suppression d'un praticien", response = String.class, tags = "DeletePraticien")
    public String deletePraticien(@PathVariable Long id) {
        System.out.print("Suppression du praticien avec l'ID : " + id);

        String serviceUrl = "http://praticien-service/api/praticiens/delete/" + id;

        try {
            restTemplate.delete(serviceUrl);
            return "Le praticien avec l'ID " + id + " a été supprimé avec succès.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la suppression du praticien avec l'ID " + id;
        }
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/dossiermedical/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Suppression d'un dossier medical", response = String.class, tags = "DeleteDossierMedical")
    public String deleteDossierMedical(@PathVariable Long id) {
        System.out.print("Suppression du dossier médical avec l'ID : " + id);

        String serviceUrl = "http://dossiermedical-service/api/dossiermedical/delete/" + id;

        try {
            restTemplate.delete(serviceUrl);
            return "Le dossier médical avec l'ID " + id + " a été supprimé avec succès.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la suppression du dossier médical avec l'ID " + id;
        }
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/rendezvous/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Suppression d'un rendez-vous", response = String.class, tags = "DeleteRendezVous")
    public String deleteRendezVous(@PathVariable Long id) {
        System.out.print("Suppression du rendez-vous avec l'ID : " + id);

        String serviceUrl = "http://rendezvous-service/api/rendezvous/delete/" + id;

        try {
            restTemplate.delete(serviceUrl);
            return "Le rendez-vous avec l'ID " + id + " a été supprimé avec succès.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la suppression du rendez-vous avec l'ID " + id;
        }
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/patients/consultaion", method = RequestMethod.GET)
    @ApiOperation(value = "Liste des consultations vue du patient", response = String.class, tags = "AllConsultationPatients")
    public String getConsultation() {

        System.out.print("Recuperation des consultations");

        String serviceUrl = "http://patient-service/api/patients/consultation";

        String response = restTemplate.getForObject(serviceUrl, String.class);

        return "Liste des consultations : " + response;
    }


    @HystrixCommand(fallbackMethod = "fallbackForRemoteCall")
    @RequestMapping(value = "/api/praticien/consultaion", method = RequestMethod.GET)
    @ApiOperation(value = "Liste des consultations vue du praticien", response = String.class, tags = "AllConsultationPraticiens")
    public String getConsultationPraticien() {
        System.out.print("Recuperation des consultations vue du praticien");
        String serviceUrl = "http://praticien-service/api/praticien/consultation";
        String response = restTemplate.getForObject(serviceUrl, String.class);
        return "Liste des consultations : " + response;
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
