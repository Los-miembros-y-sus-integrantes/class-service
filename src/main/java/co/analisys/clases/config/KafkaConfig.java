package co.analisys.clases.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import co.analisys.clases.dto.OcupacionClaseDTO;

@Configuration
public class KafkaConfig {
    @Bean
    public KafkaTemplate<String, OcupacionClaseDTO> kafkaTemplate(ProducerFactory<String, OcupacionClaseDTO> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}