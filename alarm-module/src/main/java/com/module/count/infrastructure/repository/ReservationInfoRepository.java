package com.module.count.infrastructure.repository;


import kr.or.connect.reservation.core.domain.ReservationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfo,Long> {
    @Query("SELECT reservationInfo " +
            "FROM ReservationInfo AS reservationInfo " +
            "JOIN FETCH reservationInfo.displayInfo AS displayInfo " +
            "WHERE displayInfo.openingHour BETWEEN :before and :after ")
    List<ReservationInfo> findByTimePeriod(@Param("before") LocalDateTime before, @Param("after") LocalDateTime after);
}