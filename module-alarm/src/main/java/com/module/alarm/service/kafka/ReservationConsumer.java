package com.module.alarm.service.kafka;

import com.module.alarm.slack.NotificationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ReservationConsumer {

    @Autowired
    private NotificationManager notificationManager;

    @KafkaListener(topics = "reservation", groupId = "foo")
    public void consume(String message) throws IOException {
        log.info(String.format("Consumed message : %s", message));
        notificationManager.sendNotification(message);
    }
}
