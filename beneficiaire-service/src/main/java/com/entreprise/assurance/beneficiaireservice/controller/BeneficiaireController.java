package com.entreprise.assurance.beneficiaireservice.controller;

import com.entreprise.assurance.beneficiaireservice.dto.ChangeInput;
import com.entreprise.assurance.beneficiaireservice.dto.ChangeDTO;
import com.entreprise.assurance.beneficiaireservice.model.Beneficiaire;
import com.entreprise.assurance.beneficiaireservice.repository.BeneficiaireRepository;
import com.entreprise.assurance.beneficiaireservice.security.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/beneficiaires")
public class BeneficiaireController {

    @Autowired
    private RestTemplate rest;

    @Autowired
    private BeneficiaireRepository repo;

    @PostMapping("/change")
    public ChangeDTO change(@RequestBody ChangeInput in) {
        Long tenant = TenantContext.getCurrentTenant();

        // 1) Validation de l’identité
        @SuppressWarnings("unchecked")
        Map<String, Object> emp = rest.getForObject(
            "http://localhost:8081/employe/" + in.getEmployeId(),
            Map.class
        );
        if (emp == null
            || !Objects.equals(emp.get("nom"), in.getNomEmploye())
            || !Objects.equals(emp.get("numeroEmploye"), in.getNumeroEmploye())
            || !Objects.equals(emp.get("adresse"), in.getAdresse())
            || !Objects.equals(emp.get("nas"), in.getNas())
        ) {
            throw new RuntimeException("Identité non confirmée");
        }

        // 2) Recherche de l'ancien bénéficiaire (facultatif)
        Beneficiaire old = repo.findAll().stream()
            .filter(b -> b.getEmployeId().equals(in.getEmployeId()))
            .findFirst()
            .orElse(null);

        // 3) Construction du DTO de changement
        ChangeDTO dto = new ChangeDTO(
            in.getEmployeId(),
            old != null ? old.getNom() : null,
            old != null ? old.getPrenom() : null,
            in.getNom(),
            in.getPrenom(),
            LocalDateTime.now()
        );

        // 4) Persistance du nouveau bénéficiaire
        Beneficiaire nouveau = new Beneficiaire();
        nouveau.setNom(in.getNom());
        nouveau.setPrenom(in.getPrenom());
        nouveau.setLienParente(in.getLienParente());
        nouveau.setEmployeId(in.getEmployeId());
        nouveau.setEntrepriseId(tenant);
        repo.save(nouveau);

        // 5) Notification synchrone
        rest.postForObject(
            "http://localhost:8083/notify",
            dto,
            Void.class
        );

        return dto;
    }
}
