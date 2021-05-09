package com.example.demo.infrastructure.enums;

import com.example.demo.presentation.dto.ReservationSearchDate;

import java.time.LocalDateTime;

public enum LeftReservationDate {
    ONEDAYBEFORE(1), WEEKBEFORE(7);
    private final int day;

    LeftReservationDate(int day) {
        this.day = day;
    }

    public ReservationSearchDate getStartTimeInclusive(LocalDateTime now, int minuteInterval) {
        return ReservationSearchDate.builder()
                .startTimeInclusive(now.plusDays(day))
                .endTimeExclusive(now.plusDays(day).plusMinutes(minuteInterval))
                .build();
    }
}
