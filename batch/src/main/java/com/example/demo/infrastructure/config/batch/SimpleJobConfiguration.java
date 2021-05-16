package com.example.demo.infrastructure.config.batch;

import kr.or.connect.reservation.core.presentation.domain.ReservationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j // log 사용을 위한 lombok 어노테이션
@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
@Configuration
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private int chunkSize = 10;

    @Bean
    public Job jpaPagingItemReaderJob() {
        return jobBuilderFactory.get("jpaPagingItemReaderJob")
                .start(jpaPagingItemReaderStep())
                .build();
    }

    @Bean
    @JobScope
    public Step jpaPagingItemReaderStep() {
        return stepBuilderFactory.get("jpaPagingItemReaderStep")
                .<ReservationInfo, ReservationInfo>chunk(chunkSize)
                .reader(jpaPagingItemReader(null,null))
                .writer(jpaPagingItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<ReservationInfo> jpaPagingItemReader(
            @Value("#{jobParameters[startTimeInclusive]}") String startTimeInclusive, @Value("#{jobParameters[endTimeExclusive]}") String endTimeExclusive) {

        LocalDateTime startTimeInclusiveDateTime = LocalDateTime.parse(startTimeInclusive, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS"));
        LocalDateTime endTimeExclusiveDateTime = LocalDateTime.parse(endTimeExclusive, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS"));

        Map<String, Object> params = new HashMap<>();
        params.put("startTimeInclusive", startTimeInclusiveDateTime);
        params.put("endTimeExclusive", endTimeExclusiveDateTime);
        log.info(">>>>>>>>>>> after startTimeInclusive={}, endTimeExclusive={}", startTimeInclusiveDateTime, endTimeExclusiveDateTime);

        return new JpaPagingItemReaderBuilder<ReservationInfo>()
                .name("jpaPagingItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(chunkSize)
                .queryString(
                        "SELECT reservationInfo " +
                        "FROM ReservationInfo AS reservationInfo " +
                        "JOIN FETCH reservationInfo.displayInfo AS displayInfo " +
                        "WHERE displayInfo.openingHour >= :startTimeInclusive and displayInfo.openingHour < :endTimeExclusive " +
                        "order by reservationInfo.id asc"
                )
                .parameterValues(params)
                .build();
    }

    private ItemWriter<ReservationInfo> jpaPagingItemWriter() {
        return list -> {
            for (ReservationInfo reservationInfo: list) {
                log.info("Current ReservationInfo={}", reservationInfo);
            }
        };
    }
}
