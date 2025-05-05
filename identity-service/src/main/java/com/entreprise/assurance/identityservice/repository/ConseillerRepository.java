package com.entreprise.assurance.identityservice.repository;

import com.entreprise.assurance.identityservice.model.Conseiller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConseillerRepository extends JpaRepository<Conseiller, Long> {
    Conseiller findByUsername(String username);
}