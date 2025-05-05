package com.entreprise.assurance.employeservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employe")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(name = "numero_employe", unique = true, nullable = false)
    private String numeroEmploye;

    @Column(nullable = false)
    private String adresse;

    @Column(name = "nas", nullable = false)
    private String nas;

    @Column(name = "entreprise_id", nullable = false)
    private Long entrepriseId;
}
