package com.example.dossiermedicalservice.controller;

import com.example.dossiermedicalservice.beans.Dossier_medical;
import com.example.dossiermedicalservice.service.DossierMedicalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dossiermedical")
@Api(value = "Dossier medical controller", description = "Gerer les dossiers")
public class DossierMedicalController {

    private  DossierMedicalService dossierMedicalService;

    @GetMapping
    @ApiOperation(value = "Liste de tous les dossiers", response = List.class, tags = "GetAllDossierMedical")
    public List<Dossier_medical> getAllDossiers() {
        return dossierMedicalService.getAllDossiers();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Afficher un dossier", response = Dossier_medical.class, tags = "GetDossierMedicalById")
    public Dossier_medical getDossierById(Long id) {
        return dossierMedicalService.getDossierById(id);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Creation d'un dossier medical", response = String.class, tags = "AddDossierMedical")
    public String addDossierMedical(@RequestBody Dossier_medical dossierMedical) {
        return dossierMedicalService.addDossier(dossierMedical);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Suppression d'un dossier medical", response = String.class, tags = "DeleteDossierMedical")
    public String deleteDossierMedical(Long id) {
        return dossierMedicalService.deleteDossier(id);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Mise à jour d'un dossier médical", response = String.class, tags = "UpdateDossierMedical")
    public String updateDossierMedical(@RequestBody Dossier_medical dossierMedical) {
        return dossierMedicalService.updateDossier(dossierMedical);
    }
}
