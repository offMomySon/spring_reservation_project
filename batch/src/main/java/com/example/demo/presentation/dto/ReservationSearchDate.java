package com.example.demo.presentation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ReservationSearchDate {
    private LocalDateTime startTimeInclusive;
    private LocalDateTime endTimeExclusive;
}
