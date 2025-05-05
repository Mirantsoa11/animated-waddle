package com.entreprise.assurance.notificationservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
// AJOUTEZ CET IMPORT :
import com.entreprise.assurance.notificationservice.model.ChangeDTO;

@Service
public class AssuranceService {

    private final WebClient webClient;

    public AssuranceService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                            .baseUrl("http://assurance-company")
                            .build();
    }

    public void envoyerNotification(ChangeDTO dto) {
        webClient.post()
                 .uri("/api/beneficiaires/change")
                 .bodyValue(dto)
                 .retrieve()
                 .bodyToMono(Void.class)
                 .block();
    }
}
