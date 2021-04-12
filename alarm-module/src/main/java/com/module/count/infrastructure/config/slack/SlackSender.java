package com.module.count.infrastructure.config.slack;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Data
@Configuration
public class SlackSender {

    private final NotificationProperty slackProperty;

    public SlackSender(NotificationProperty slackProperty) {
        this.slackProperty = slackProperty;
    }

    public void sendSlack(String contents) {
        log.info("sendSlack called");
        if (slackProperty.isEnabled()) {
            try { // create slack Message
                SlackMessage slackMessage = new SlackMessage(contents, slackProperty.getChannel(), slackProperty.getBotName(), slackProperty.getIcon().getEmoji(), slackProperty.getIcon().getUrl());

                ObjectMapper objectMapper = new ObjectMapper();
                String payload = objectMapper.writeValueAsString(slackMessage);

                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

                // send the post request
                log.info("payload : " + payload);
                log.info("url : " + slackProperty.getWebhook().getUrl());
                HttpEntity<String> entity = new HttpEntity<>(payload, headers);
                log.info("Entity = " + entity);
                restTemplate.postForEntity(slackProperty.getWebhook().getUrl(), entity, String.class);
            } catch (Exception e) {
                log.error("Unhandled Exception occurred while send slack. [Reason] : ", e);
            }
        }
    }

}