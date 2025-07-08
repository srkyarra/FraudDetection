package org.example.Producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
        private final KafkaTemplate<String, String> kafkaTemplate;

        public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
            this.kafkaTemplate = kafkaTemplate;
        }

        public void sendAlert(String message) {
            kafkaTemplate.send("fraud-alerts", message);
        }
    }

