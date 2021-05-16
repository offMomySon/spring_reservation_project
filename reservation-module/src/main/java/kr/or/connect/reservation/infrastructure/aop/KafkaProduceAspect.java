package kr.or.connect.reservation.infrastructure.aop;

import kr.or.connect.reservation.application.service.kafka.ReservationProducer;
import kr.or.connect.reservation.core.presentation.dto.ReservationRequestResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@RequiredArgsConstructor
@Component
public class KafkaProduceAspect {
    private final ReservationProducer producer;

    @AfterReturning(
            pointcut = "execution(* kr.or.connect.reservation.application.service.impl.ReservationServiceImpl.addReservation(..))",
            returning = "requestResult"
    )
    public ReservationRequestResult afterReturningAddReservation(JoinPoint joinPoint, ReservationRequestResult requestResult) {
        log.info("Aspect(@AfterReturning) :: afterReturningAddReservation is executed after " + joinPoint.getSignature().toShortString());
        log.info("Reservation Id : " + requestResult.getReservationEmail());

        producer.sendMessage(requestResult);

        return requestResult;
    }
}
