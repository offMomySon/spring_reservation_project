package kr.or.connect.reservation.core.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "coolsms.api")
public class CoolSmsAPI {
    private String key;
    private String secret;
    private String sendNo;
}