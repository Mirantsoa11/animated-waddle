package com.entreprise.assurance.apigateway.bff;

import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.List;
import java.time.LocalDateTime;

@Controller
public class BffGraphqlController {

    @Autowired
    private RestTemplate restTemplate;

    @QueryMapping
    public Employe employe(@Argument Long id) {
        Map<String, Object> response = restTemplate.getForObject("http://localhost:8082/employe/" + id, Map.class);
        return mapToEmploye(response);
    }

    @QueryMapping
    public List<Employe> employes() {
        List<Map<String, Object>> response = restTemplate.getForObject("http://localhost:8082/employe/list", List.class);
        return response.stream()
                .map(this::mapToEmploye)
                .toList();
    }

    @MutationMapping
    public ChangeResult changeBeneficiaire(@Argument("input") ChangeBeneficiaireInput input) {
        Map<String, Object> response = restTemplate.postForObject("http://localhost:8085/beneficiaires/change", input, Map.class);
        return mapToChangeResult(response);
    }

    @QueryMapping
    public ChangeResult changeResult(@Argument Long employeId) {
        Map<String, Object> response = restTemplate.getForObject("http://localhost:8085/beneficiaires/change/" + employeId, Map.class);
        return mapToChangeResult(response);
    }

    private Employe mapToEmploye(Map<String, Object> data) {
        Employe employe = new Employe();
        employe.setId(data.get("id").toString());
        employe.setNom(data.get("nom").toString());
        employe.setNumeroEmploye(data.get("numeroEmploye").toString());
        employe.setAdresse(data.get("adresse").toString());
        employe.setNas(data.get("nas").toString());
        employe.setEntrepriseId(data.get("entrepriseId").toString());
        return employe;
    }

    private ChangeResult mapToChangeResult(Map<String, Object> data) {
        ChangeResult result = new ChangeResult();
        result.setEmployeId(data.get("employeId").toString());
        result.setAncienNom((String) data.get("ancienNom"));
        result.setAncienPrenom((String) data.get("ancienPrenom"));
        result.setNouveauNom(data.get("nouveauNom").toString());
        result.setNouveauPrenom(data.get("nouveauPrenom").toString());
        result.setDateModification(data.get("dateModification").toString());
        return result;
    }
}

class Employe {
    private String id;
    private String nom;
    private String numeroEmploye;
    private String adresse;
    private String nas;
    private String entrepriseId;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getNumeroEmploye() { return numeroEmploye; }
    public void setNumeroEmploye(String numeroEmploye) { this.numeroEmploye = numeroEmploye; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getNas() { return nas; }
    public void setNas(String nas) { this.nas = nas; }
    public String getEntrepriseId() { return entrepriseId; }
    public void setEntrepriseId(String entrepriseId) { this.entrepriseId = entrepriseId; }
}

class ChangeResult {
    private String employeId;
    private String ancienNom;
    private String ancienPrenom;
    private String nouveauNom;
    private String nouveauPrenom;
    private String dateModification;

    // Getters and setters
    public String getEmployeId() { return employeId; }
    public void setEmployeId(String employeId) { this.employeId = employeId; }
    public String getAncienNom() { return ancienNom; }
    public void setAncienNom(String ancienNom) { this.ancienNom = ancienNom; }
    public String getAncienPrenom() { return ancienPrenom; }
    public void setAncienPrenom(String ancienPrenom) { this.ancienPrenom = ancienPrenom; }
    public String getNouveauNom() { return nouveauNom; }
    public void setNouveauNom(String nouveauNom) { this.nouveauNom = nouveauNom; }
    public String getNouveauPrenom() { return nouveauPrenom; }
    public void setNouveauPrenom(String nouveauPrenom) { this.nouveauPrenom = nouveauPrenom; }
    public String getDateModification() { return dateModification; }
    public void setDateModification(String dateModification) { this.dateModification = dateModification; }
}

class ChangeBeneficiaireInput {
    private String employeId;
    private String nom;
    private String prenom;
    private String lienParente;
    private String nomEmploye;
    private String numeroEmploye;
    private String adresse;
    private String nas;

    // Getters and setters
    public String getEmployeId() { return employeId; }
    public void setEmployeId(String employeId) { this.employeId = employeId; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getLienParente() { return lienParente; }
    public void setLienParente(String lienParente) { this.lienParente = lienParente; }
    public String getNomEmploye() { return nomEmploye; }
    public void setNomEmploye(String nomEmploye) { this.nomEmploye = nomEmploye; }
    public String getNumeroEmploye() { return numeroEmploye; }
    public void setNumeroEmploye(String numeroEmploye) { this.numeroEmploye = numeroEmploye; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getNas() { return nas; }
    public void setNas(String nas) { this.nas = nas; }
}
