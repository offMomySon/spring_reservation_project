package kr.or.connect.reservation.application.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReservationProducer {
    private static final String TOPIC = "reservation";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ReservationProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        log.info(String.format("Produce message : %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}
