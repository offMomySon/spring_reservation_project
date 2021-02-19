package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.ReservationUserComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationUserCommentRepository extends JpaRepository<ReservationUserComment, Long> {
    boolean existsByReservationInfoId(long reservationInfoId);

    List<ReservationUserComment> findByReservationInfoId(long reservationInfoId);
}
