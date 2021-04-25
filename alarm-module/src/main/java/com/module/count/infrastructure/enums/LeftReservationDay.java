package com.module.count.infrastructure.enums;

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
