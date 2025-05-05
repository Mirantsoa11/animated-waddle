package com.entreprise.assurance.employeservice.graphql;

import com.entreprise.assurance.employeservice.dto.EmployeInput;
import com.entreprise.assurance.employeservice.model.Employe;
import com.entreprise.assurance.employeservice.service.EmployeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmployeResolver {
    private static final Logger logger = LoggerFactory.getLogger(EmployeResolver.class);

    @Autowired
    private EmployeService employeService;

    @QueryMapping
    public Employe employe(@Argument Long id) {
        return employeService.findById(id);
    }

    @QueryMapping
    public List<Employe> employes() {
        return employeService.findAll();
    }

    @MutationMapping
    public Employe createEmploye(@Argument("input") EmployeInput input) {
        try {
            logger.info("Création d'un nouvel employé avec les données: {}", input);
            Employe employe = new Employe();
            employe.setNom(input.getNom());
            employe.setNumeroEmploye(input.getNumeroEmploye());
            employe.setAdresse(input.getAdresse());
            employe.setNas(input.getNas());
            employe.setEntrepriseId(input.getEntrepriseId().longValue());
            Employe savedEmploye = employeService.save(employe);
            logger.info("Employé créé avec succès: {}", savedEmploye);
            return savedEmploye;
        } catch (Exception e) {
            logger.error("Erreur lors de la création de l'employé", e);
            throw e;
        }
    }

    @MutationMapping
    public Employe updateEmploye(@Argument Long id, @Argument("input") EmployeInput input) {
        try {
            logger.info("Mise à jour de l'employé {} avec les données: {}", id, input);
            Employe existingEmploye = employeService.findById(id);
            if (existingEmploye != null) {
                existingEmploye.setNom(input.getNom());
                existingEmploye.setNumeroEmploye(input.getNumeroEmploye());
                existingEmploye.setAdresse(input.getAdresse());
                existingEmploye.setNas(input.getNas());
                existingEmploye.setEntrepriseId(input.getEntrepriseId().longValue());
                Employe updatedEmploye = employeService.save(existingEmploye);
                logger.info("Employé mis à jour avec succès: {}", updatedEmploye);
                return updatedEmploye;
            }
            return null;
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour de l'employé", e);
            throw e;
        }
    }

    @MutationMapping
    public Boolean deleteEmploye(@Argument Long id) {
        try {
            logger.info("Suppression de l'employé {}", id);
            employeService.deleteById(id);
            logger.info("Employé supprimé avec succès");
            return true;
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de l'employé", e);
            return false;
        }
    }
} 