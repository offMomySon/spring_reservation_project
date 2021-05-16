package kr.or.connect.reservation.infrastructure.repository;

import kr.or.connect.reservation.core.presentation.domain.ReservationUserComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationUserCommentRepository extends JpaRepository<ReservationUserComment, Long> {
    List<ReservationUserComment> findByReservationInfoId(long reservationInfoId, Pageable pageable);
}
