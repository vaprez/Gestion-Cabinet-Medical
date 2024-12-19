package com.example.patientservice.service;

import com.example.patientservice.Beans.Dossier_medical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DossierMedicalService {
    @Lazy
    @Autowired
    RestTemplate restTemplate;

    public Dossier_medical getDossierMedical(Long id) {
        String url = "http://localhost:8083/api/dossiermedical/" + id;
        return restTemplate.getForObject(url, Dossier_medical.class);
    }

    public String updateDossierMedical(Dossier_medical dossierMedical){
        String url = "http://localhost:8083/api/dossiermedical/update";
        return restTemplate.postForObject(url, dossierMedical, String.class);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
