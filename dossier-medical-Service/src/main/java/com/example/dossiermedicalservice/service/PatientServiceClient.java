package com.example.dossiermedicalservice.service;

import com.example.dossiermedicalservice.beans.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientServiceClient {
    @Lazy
    @Autowired
    RestTemplate restTemplate;


    public Patient getPatientById(Long id) {
        String url = "http://localhost:8081/api/patients/" + id;
        return restTemplate.getForObject(url, Patient.class);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

