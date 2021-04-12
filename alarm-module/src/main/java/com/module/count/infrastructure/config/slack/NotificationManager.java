package com.module.count.infrastructure.config.slack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationManager {

    private final SlackSender slackSender;

    public NotificationManager(SlackSender slackSender) {
        this.slackSender = slackSender;
    }

    public void sendNotification(String userEmail) {
        log.info("#### send Notification.");

        // send slack
        slackSender.sendSlack(userEmail + " has Generated Reservation.");
    }

    /**
     * generated Message.
     *
     * @return
     */
    private String generatedMessage() {
        StringBuilder sb = new StringBuilder();

        sb.append("[Notification]").append(System.getProperty("line.separator"))
                .append("[Name] : ").append("Tester").append(System.getProperty("line.separator"))
                .append("[Message] : ").append("테스트 메시지 !!");

        return sb.toString();
    }
}
