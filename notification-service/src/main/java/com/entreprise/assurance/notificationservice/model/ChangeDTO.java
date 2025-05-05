package com.entreprise.assurance.notificationservice.model;

import java.time.LocalDateTime;

public class ChangeDTO {
    private Long employeId;
    private String ancienNom, ancienPrenom;
    private String nouveauNom, nouveauPrenom;
    private LocalDateTime dateModification;
    // getters and setters
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