package kr.or.connect.reservation.core.presentation.domain.audite;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class ReservationBaseEntity extends BaseEntity {
    @CreatedDate
    @Column(name = "reservation_date", updatable = false)
    private LocalDateTime reservationDate;
}

