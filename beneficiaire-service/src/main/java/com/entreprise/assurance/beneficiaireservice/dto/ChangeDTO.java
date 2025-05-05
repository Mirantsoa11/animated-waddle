package com.entreprise.assurance.beneficiaireservice.dto;

import java.time.LocalDateTime;

public class ChangeDTO {
    private Long employeId;
    private String ancienNom, ancienPrenom;
    private String nouveauNom, nouveauPrenom;
    private LocalDateTime dateModification;

    // Constructor
    public ChangeDTO(Long employeId, String ancienNom, String ancienPrenom, 
                     String nouveauNom, String nouveauPrenom, LocalDateTime dateModification) {
        this.employeId = employeId;
        this.ancienNom = ancienNom;
        this.ancienPrenom = ancienPrenom;
        this.nouveauNom = nouveauNom;
        this.nouveauPrenom = nouveauPrenom;
        this.dateModification = dateModification;
    }

    // Getters and Setters
    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public String getAncienNom() {
        return ancienNom;
    }

    public void setAncienNom(String ancienNom) {
        this.ancienNom = ancienNom;
    }

    public String getAncienPrenom() {
        return ancienPrenom;
    }

    public void setAncienPrenom(String ancienPrenom) {
        this.ancienPrenom = ancienPrenom;
    }

    public String getNouveauNom() {
        return nouveauNom;
    }

    public void setNouveauNom(String nouveauNom) {
        this.nouveauNom = nouveauNom;
    }

    public String getNouveauPrenom() {
        return nouveauPrenom;
    }

    public void setNouveauPrenom(String nouveauPrenom) {
        this.nouveauPrenom = nouveauPrenom;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }
}
