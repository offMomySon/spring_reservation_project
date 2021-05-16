package com.module.count.infrastructure.enums;

import java.time.LocalDateTime;
import java.util.List;

public enum LeftReservationDay {
    ONE(1), WEEK(7);
    private int day;

    LeftReservationDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
