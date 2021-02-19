package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.DisplayInfoImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DisplayInfoImageRepository extends JpaRepository<DisplayInfoImage, Long> {
    @Query("SELECT displayInfoImage " +
            "FROM DisplayInfoImage displayInfoImage " +
            "JOIN FETCH displayInfoImage.fileInfo " +
            "WHERE displayInfoImage.displayInfo.id = :displayInfoId")
    Optional<DisplayInfoImage> findOneByDisplayInfoId(@Param("displayInfoId") long displayInfoId);
}
