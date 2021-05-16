package kr.or.connect.reservation.infrastructure.repository;

import kr.or.connect.reservation.core.presentation.domain.ReservationUserCommentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReservationUserCommentImageRepository extends JpaRepository<ReservationUserCommentImage, Long> {
    @Query("SELECT reservationUserCommentImage FROM ReservationUserCommentImage reservationUserCommentImage " +
            "JOIN FETCH reservationUserCommentImage.fileInfo fileInfo " +
            "WHERE reservationUserCommentImage.reservationUserComment.id = :reservationUserCommentId")
    Optional<ReservationUserCommentImage> findOneByReservationUserCommentId(@Param("reservationUserCommentId") long reservationUserCommentId);
}
