package com.example.patientservice.Beans;

public class Doctor {
    private Long id;
    private String name;
    private String speciality;

    public Doctor(long id, String name, String speciality) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
    }
    public Doctor(){}
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

}

