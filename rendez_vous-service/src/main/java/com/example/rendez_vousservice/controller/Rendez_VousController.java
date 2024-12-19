package com.example.rendez_vousservice.controller;

import com.example.rendez_vousservice.beans.Rendez_vous;
import com.example.rendez_vousservice.service.Rendez_vousService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/rendezvous")
@Api(value = "Rendez_vous Controller", description = "Gérer les prises de rendez_vous : CRUD ")
public class Rendez_VousController {

    @Autowired
    private Rendez_vousService service;

    @GetMapping
    @ApiOperation(value = "Liste de tous les rendez_vous", response = List.class,tags = "GetAllRendezVous")
    public List<Rendez_vous> getAllRendezVous() {
        return service.getRendezVousList();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Details du rendez_vous d'un utilisateur", response = Rendez_vous.class,tags = "GetRendezVousById")
    public Rendez_vous getRendezVousById(Long id) {
        return service.getRendezVousById(id);
    }

    @PostMapping("/add")
    @ApiOperation(value = "Prise de rendez_vous", response = String.class, tags = "AddRendezVous")
    public String addRendezVous(Rendez_vous rendezVous) {
        return service.addRendezVous(rendezVous);
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value="Modification d'un rendez_vous", response = String.class, tags = "UpdateRendezVous")
    public String updateRendezVous(@PathVariable Long id, Rendez_vous rendezVous) {
        return service.updateRendezVous(id,rendezVous);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value="Suppression d'un rendez_vous", response = String.class, tags = "DeleteRendezVous")
    public String deleteRendezVous(Long id) {
        return service.deleteRendezVous(id);
    }

}
