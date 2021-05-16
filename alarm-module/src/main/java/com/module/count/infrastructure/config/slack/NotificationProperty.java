package com.module.count.infrastructure.config.slack;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "notification.slack")
public class NotificationProperty {

    private boolean enabled;
    private String channel;
    private String botName;
    private Web webhook;
    private Icon icon;

    @Data
    public static class Web {
        private String url;
    }

    @Data
    public static class Icon {
        private String emoji;
        private String url;
    }
}

//
//notification:
//        slack:
//        enabled: false
//        webhook:
//        url: https://hooks.slack.com/services/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
//        channel: \#general
//        botName: incoming-webhook
//        icon:
//        emoji:
//        url: