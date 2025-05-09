package com.entreprise.assurance.employeservice.repository;

import com.entreprise.assurance.employeservice.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
}