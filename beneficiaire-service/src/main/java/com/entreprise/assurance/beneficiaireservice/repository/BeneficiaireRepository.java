package com.entreprise.assurance.beneficiaireservice.repository;

import com.entreprise.assurance.beneficiaireservice.model.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Long> {
}