package kr.or.connect.reservation.application.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ForTest_KafkaConsumer {
    @KafkaListener(topics = "reservation", groupId = "foo")
    public void consume(String message) throws IOException {
        log.info(String.format("Consumed message : %s", message));
    }
}
