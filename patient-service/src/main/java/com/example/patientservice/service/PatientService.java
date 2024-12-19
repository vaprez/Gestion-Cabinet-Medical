package com.example.patientservice.service;


import com.example.patientservice.Beans.Consultation;
import com.example.patientservice.Beans.Doctor;
import com.example.patientservice.Beans.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    // Liste des docteurs
    private List<Doctor> doctors = new ArrayList<>(Arrays.asList(
            new Doctor(1L, "Dr. Smith", "Cardiology"),
            new Doctor(2L, "Dr. Brown", "Pulmonology"),
            new Doctor(3L, "Dr. Taylor", "Endocrinology")
    ));

    // Liste des consultations
    private List<Consultation> consultations = new ArrayList<>(Arrays.asList(
            new Consultation(1L, 1L, "2024-01-10", "Routine Checkup", "Completed"),
            new Consultation(2L, 2L, "2024-02-15", "Flu Symptoms", "Pending"),
            new Consultation(3L, 3L, "2024-03-05", "Back Pain", "In Progress")
    ));


    private List<Patient> patients = new ArrayList<>(Arrays.asList(
            new Patient(1L, "John Doe", 30, "Male",1L , Arrays.asList("Diabetes", "Hypertension")),
            new Patient(2L, "Jane Doe", 25, "Female", 2L, Arrays.asList("Asthma"))
    ));

    public List<Patient> getAllPatients() {
        return patients;
    }
    public List<Doctor> getDoctors() {
        return doctors;
    }
    public List<Consultation> getConsultations() {
        return consultations;
    }

    public Optional<Patient> getPatientById(Long id) {
        return patients.stream().filter(patient -> patient.getId().equals(id)).findFirst();
    }

    public String addPatient(Patient patient) {
        patients.add(patient);
        return "Patient Added Successfully!";
    }

    public String updatePatient(Long id, Patient updatedPatient) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(id)) {
                patients.set(i, updatedPatient);
                return "Patient Updated Successfully!";
            }
        }
        return "";
    }

    public String deletePatient(Long id) {
        patients.removeIf(patient -> patient.getId().equals(id));
        return "Patient Deleted Successfully!";
    }



}
