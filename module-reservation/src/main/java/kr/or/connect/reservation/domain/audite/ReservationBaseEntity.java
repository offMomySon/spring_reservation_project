package kr.or.connect.reservation.domain.audite;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class ReservationBaseEntity extends BaseEntity {
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reservation_date", updatable = false)
    private Date reservationDate;
}

