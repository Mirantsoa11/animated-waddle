package com.entreprise.assurance.employeservice.dto;

import lombok.Data;

@Data
public class EmployeInput {
    private String nom;
    private String numeroEmploye;
    private String adresse;
    private String nas;
    private Integer entrepriseId;
} 