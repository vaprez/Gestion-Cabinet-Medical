package com.example.patientservice.Beans;

import java.util.List;

/**
 * Modèle représentant un Patient.
 * Contient les informations personnelles du patient, les informations sur son médecin,
 * et les anomalies (maladies) associées.
 */
public class Patient {
    private Long id;
    private String name;
    private int age;
    private String gender;
    private Long doctor; // Médecin traitant
    private List<String> anomalies; // Liste des maladies

    public Patient(Long id, String name, int age, String gender, Long doctor, List<String> anomalies) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.doctor = doctor;
        this.anomalies = anomalies;
    }

    public Patient() {}


    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Long getDoctor() { return doctor; }
    public void setDoctor(Long doctor) { this.doctor = doctor; }

    public List<String> getAnomalies() { return anomalies; }
    public void setAnomalies(List<String> anomalies) { this.anomalies = anomalies; }

}
