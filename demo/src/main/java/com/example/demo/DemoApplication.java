package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EntityScan("kr.or.connect.reservation.core.domain")
@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
@Slf4j
public class DemoApplication {
    @PostConstruct
    public void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        log.info("현재시각 : " + new LocalDateTime());
    }

    public static void main(String[] args) throws Exception {
//        System.exit(SpringApplication.exit(SpringApplication.run(DemoApplication.class, args)));

        SpringApplication.run(DemoApplication.class, args);
    }

}
