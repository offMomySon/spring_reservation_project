package kr.or.connect.reservation.core.presentation.domain;

import kr.or.connect.reservation.core.presentation.domain.audite.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation_user_comment")
public class ReservationUserComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "reservation_info_id")
    private ReservationInfo reservationInfo;

    private double score;

    private String comment;
}
