package com.example.patientservice.controller;

import com.example.patientservice.Beans.Consultation;
import com.example.patientservice.Beans.Patient;
import com.example.patientservice.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Optional;

/**
 * Contrôleur pour exposer les endpoints REST liés à la gestion des patients.
 */
@RestController
@RequestMapping("/api/patients")
@Api(value = "Patient Controller", description = "Gérer les patients : CRUD et consultation.")
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * Récupérer tous les patients.
     * @return Liste des patients.
     */
    @GetMapping
    @ApiOperation(value = "Renvoie une liste de tous les patients.",response = List.class,tags = "GetAllPatient")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    /**
     * Récupérer un patient par ID.
     * @param id ID du patient.
     * @return Le patient correspondant ou vide si non trouvé.
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Renvoie les détails d'un patient par ID.",response = Patient.class, tags = "GetPatientById")
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    /**
     * Ajouter un nouveau patient.
     * @param patient Patient à ajouter.
     */
    @PostMapping("/add")
    @ApiOperation(value ="Ajoute un nouveau patient dans la liste.",response = String.class,tags = "AddPatient")
    public void addPatient(@RequestBody Patient patient) {
        patientService.addPatient(patient);
    }

    /**
     * Mettre à jour un patient existant.
     * @param id ID du patient.
     * @param updatedPatient Données mises à jour.
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value = "Met à jour les données d'un patient existant.",response = String.class, tags = "UpdatePatient")
    public void updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        patientService.updatePatient(id, updatedPatient);
    }

    /**
     * Supprimer un patient.
     * @param id ID du patient à supprimer.
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Supprime un patient par son ID.", response = String.class, tags = "DeletePatient")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

    @GetMapping("/consultation")
    @ApiOperation(value = "Liste de toute les consultations",response = List.class,tags = "GetConsultations")
    public List<Consultation> getConsultations() {
        return patientService.getConsultations();
    }

}
