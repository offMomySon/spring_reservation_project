package com.module.count.service.schedule;

import com.module.count.infrastructure.enums.LeftReservationDay;
import com.module.count.infrastructure.repository.ReservationInfoRepository;
import com.module.count.service.SmsService;
import kr.or.connect.reservation.core.domain.ReservationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Component
public class ReservationAlarmScheduler {
    private final ReservationInfoRepository reservationInfoRepository;
    private final SmsService smsService;

    public ReservationAlarmScheduler(ReservationInfoRepository reservationInfoRepository, SmsService smsService) {
        this.reservationInfoRepository = reservationInfoRepository;
        this.smsService = smsService;
    }

    @Scheduled(cron = "* */1 * * * *")
    public void sendReservationAlarm() {
        log.info("스케쥴 시작");

        LocalDateTime now = LocalDateTime.now();
        log.info("현재 시간 : " + now);

        sendReservationAlarm(LeftReservationDay.ONE);

        sendReservationAlarm(LeftReservationDay.WEEK);

    }

    private void sendReservationAlarm(LeftReservationDay leftReservationDay) {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startTimeInclusive = now.plusDays(leftReservationDay.getDay());
        LocalDateTime endTimeExclusive = now.plusDays(leftReservationDay.getDay()).plusMinutes(1);

        List<ReservationInfo> oneDayBeforeReservationInfos = reservationInfoRepository.findByTimePeriod(startTimeInclusive, endTimeExclusive);

        //for log
        oneDayBeforeReservationInfos.parallelStream()
                .forEach(reservationInfo ->
                        log.info("예약 번호 - " + reservationInfo.getReservationTel() + "\t\n" +
                                "예약 날짜 : " + reservationInfo.getDisplayInfo().getOpeningHour())
                );

        oneDayBeforeReservationInfos.parallelStream()
                .forEach(reservationInfo -> {
                            smsService.sendMessage(reservationInfo.getReservationTel(), leftReservationDay.getDay());
                        }
                );

    }
}
