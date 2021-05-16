package kr.or.connect.reservation.core.presentation.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reservation_user_comment_image")
public class ReservationUserCommentImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_info_id")
    private ReservationInfo reservationInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_user_comment_id")
    private ReservationUserComment reservationUserComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;
}
