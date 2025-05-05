package com.entreprise.assurance.employeservice.controller;

import com.entreprise.assurance.employeservice.model.Employe;
import com.entreprise.assurance.employeservice.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    @Autowired
    private EmployeRepository repo;

    @GetMapping
    public List<Employe> getAllEmployes() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employe createEmploye(@RequestBody Employe employe) {
        return repo.save(employe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> updateEmploye(@PathVariable Long id, @RequestBody Employe employeDetails) {
        return repo.findById(id)
                .map(existingEmploye -> {
                    existingEmploye.setNom(employeDetails.getNom());
                    existingEmploye.setNumeroEmploye(employeDetails.getNumeroEmploye());
                    existingEmploye.setAdresse(employeDetails.getAdresse());
                    existingEmploye.setNas(employeDetails.getNas());
                    existingEmploye.setEntrepriseId(employeDetails.getEntrepriseId());
                    return ResponseEntity.ok(repo.save(existingEmploye));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmploye(@PathVariable Long id) {
        return repo.findById(id)
                .map(employe -> {
                    repo.delete(employe);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}