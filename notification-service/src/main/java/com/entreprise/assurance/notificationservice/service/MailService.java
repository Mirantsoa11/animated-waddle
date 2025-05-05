package com.entreprise.assurance.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired private JavaMailSender sender;
    public void envoyer(String to, String text) {
        SimpleMailMessage m = new SimpleMailMessage();
        m.setTo(to);
        m.setSubject("Changement de bénéficiaire");
        m.setText(text);
        sender.send(m);
    }
}