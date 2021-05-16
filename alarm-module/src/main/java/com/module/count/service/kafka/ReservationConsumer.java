package com.module.count.service.kafka;

import com.module.count.infrastructure.config.slack.NotificationManager;
import kr.or.connect.reservation.core.presentation.dto.ReservationRequestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Component
public class ReservationConsumer {
    private final NotificationManager notificationManager;

    public ReservationConsumer(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    @KafkaListener(topics = "${kafka.topic-name}", groupId = "${kafka.group-id}", containerFactory = "pushEntityKafkaListenerContainerFactory")
    public void consume(@Payload ReservationRequestResult requestResult,
                        @Headers MessageHeaders messageHeaders) {
        log.info(String.format("Consumed message : %s", requestResult));

        notificationManager.sendNotification(requestResult.getReservationEmail());
    }
}
