package com.example.dossiermedicalservice.service;

import com.example.dossiermedicalservice.beans.Praticien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PraticienServiceClient {

    @Lazy
    @Autowired
    RestTemplate restTemplate;

    public Praticien getPraticienById(Long id) {
        String url = "http://localhost:8082/api/praticiens/" + id;
        return restTemplate.getForObject(url, Praticien.class);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
