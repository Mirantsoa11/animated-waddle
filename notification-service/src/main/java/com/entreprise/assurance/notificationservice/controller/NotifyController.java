package com.entreprise.assurance.notificationservice.controller;

import com.entreprise.assurance.notificationservice.model.ChangeDTO;
import com.entreprise.assurance.notificationservice.service.AssuranceService;
import com.entreprise.assurance.notificationservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotifyController {

    @Autowired
    private MailService mailService;

    @Autowired
    private AssuranceService assuranceService;

    /**
     * Point d'entrée synchrone pour être appelé par le BeneficiaireService
     */
    @PostMapping("/notify")
    public void notifyClient(@RequestBody ChangeDTO dto) {
        // 1) Envoi de l’e-mail de confirmation
        String to = "employee" + dto.getEmployeId() + "@example.com";
        String text = String.format(
            "Bonjour,\n\nVotre bénéficiaire a été modifié.\nNouveau bénéficiaire : %s %s\nDate : %s",
            dto.getNouveauNom(),
            dto.getNouveauPrenom(),
            dto.getDateModification()
        );
        mailService.envoyer(to, text);

        // 2) Appel HTTP synchrone vers l’API de l’assureur
        assuranceService.envoyerNotification(dto);
    }
}
