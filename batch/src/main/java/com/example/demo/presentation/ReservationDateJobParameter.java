package com.example.demo.presentation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Getter
@NoArgsConstructor
public class ReservationDateJobParameter {

    private LocalDateTime startTimeInclusive;

    private LocalDateTime endTimeExclusive;

    @Value("#{jobParameters[startTimeInclusive]}")
    public void setStartTimeInclusive(String startTimeInclusive) {
        log.info("startTimeInclusive : " + startTimeInclusive);
        this.startTimeInclusive = LocalDateTime.parse(startTimeInclusive, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Value("#{jobParameters[endTimeExclusive]}")
    public void setEndTimeExclusive(String endTimeExclusive) {
        log.info("endTimeExclusive : " + endTimeExclusive);
        this.endTimeExclusive = LocalDateTime.parse(endTimeExclusive, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
