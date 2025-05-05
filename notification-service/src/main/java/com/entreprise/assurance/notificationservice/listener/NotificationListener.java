package com.entreprise.assurance.notificationservice.listener;

import com.entreprise.assurance.notificationservice.event.ChangeEvent;
import com.entreprise.assurance.notificationservice.model.ChangeDTO;
import com.entreprise.assurance.notificationservice.service.AssuranceService;
import com.entreprise.assurance.notificationservice.service.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    @Autowired private MailService mail;
    @Autowired private AssuranceService assure;
    @RabbitListener(queues = "beneficiaire.change")
    public void onChange(ChangeEvent evt) {
        ChangeDTO dto = evt.getChange();
        mail.envoyer("employee" + dto.getEmployeId() + "@example.com", 
            "Votre bénéficiaire a été changé en " + dto.getNouveauNom());
        assure.envoyerNotification(dto);
    }
}