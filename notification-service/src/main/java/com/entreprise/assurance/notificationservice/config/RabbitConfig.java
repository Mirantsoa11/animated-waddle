package com.entreprise.assurance.notificationservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    
    @Bean
    public Queue beneficiaireChangeQueue() {
        return new Queue("beneficiaire.change", true); // true = durable
    }
} 