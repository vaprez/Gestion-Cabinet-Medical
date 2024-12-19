package com.example.praticienservice.controller;

import com.example.praticienservice.beans.Consultation;
import com.example.praticienservice.beans.Praticien;
import com.example.praticienservice.service.PraticienService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/praticien")
@Api(value = "Praticien Controller", description = "Gérer les Praticien : CRUD et consultation.")
public class PraticienController {

    @Autowired
    private PraticienService service;
    @Autowired
    private PraticienService praticienService;

    @GetMapping
    @ApiOperation(value = "Renvoie une liste de tous les praticiens.",response = List.class,tags = "GetAllPraticien")
    public List<Praticien> getAllPraticien() {
        return service.getPraticiens();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Renvoie un praticien selon son id.",response = Praticien.class,tags = "GetPraticienById")
    public Praticien getPraticienById(Long id) {
        return service.getPraticien(id);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Ajoute un nouveau praticien dans la liste.",response = String.class,tags = "AddPraticien")
    public String addPraticien(Praticien praticien) {
        return service.addPraticien(praticien);
    }

    @PutMapping("/update")
    @ApiOperation(value = "Met à jour le praticien dans la liste.",response = String.class,tags = "UpdatePraticien")
    public String updatePraticien(Praticien praticien) {
        return service.updatePraticien(praticien);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Supprime le praticien dans la liste.",response = String.class,tags = "DeletePraticien")
    public String deletePraticien(Long id) {
        return service.deletePraticien(id);
    }

    @GetMapping("/consultation")
    @ApiOperation(value = "Liste de toute les consultations",response = List.class,tags = "GetConsultations")
    public List<Consultation> getConsultations() {
        return praticienService.getConsultations();
    }




}
