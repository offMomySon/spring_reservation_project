package com.module.count.service.schedule;

import com.module.count.infrastructure.repository.ReservationInfoRepository;
import kr.or.connect.reservation.core.domain.ReservationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class ReservationAlarmScheduler {
    final private ReservationInfoRepository reservationInfoRepository;

    public ReservationAlarmScheduler(ReservationInfoRepository reservationInfoRepository) {
        this.reservationInfoRepository = reservationInfoRepository;
    }

    @Scheduled(cron = "* */1 * * * *")
    public void sendReservationAlarm() {
        log.info("스케쥴 시작");

        LocalDateTime now = LocalDateTime.now();
        log.info("현재 시간 : " + now);

        LocalDateTime oneDateBefore = now.plusDays(1);
        LocalDateTime oneDateAfter = now.plusDays(1).plusMinutes(30);
        log.info("oneDateBefore : " + oneDateBefore);
        log.info("oneDateAfter : " + oneDateAfter);

        sendReservationAlarm(oneDateBefore, oneDateAfter);

        LocalDateTime weekBefore = now.plusDays(7);
        LocalDateTime weekAfter = now.plusDays(7).plusMinutes(30);

        sendReservationAlarm(weekBefore, weekAfter);

    }

    private void sendReservationAlarm(LocalDateTime startTimeInclusive, LocalDateTime endTimeExclusive) {
        List<ReservationInfo> oneDayBeforeReservationInfos = reservationInfoRepository.findByTimePeriod(startTimeInclusive, endTimeExclusive);

        oneDayBeforeReservationInfos.stream()
                .forEach(reservationInfo ->
                        log.info("예약 번호 - " + reservationInfo.getReservationTel() + "\t\n" +
                                "예약 날짜 : " + reservationInfo.getDisplayInfo().getOpeningHour())
                );
    }
}
