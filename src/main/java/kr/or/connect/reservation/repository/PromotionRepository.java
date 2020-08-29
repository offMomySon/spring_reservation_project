package kr.or.connect.reservation.repository;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.or.connect.reservation.dto.PromotionRs;
import kr.or.connect.reservation.model.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

	@Nonnull
	@Query("SELECT "
			+ "new kr.or.connect.reservation.dto.PromotionRs "
			+ "( "
			+ "pm.id, "
			+ "pd.id, "
			+ "fi.saveFileName "
			+ " ) "
			+ "FROM Product pd "
			+ "JOIN pd.promotions pm "
			+ "JOIN pd.productImages pi "
			+ "JOIN pi.fileInfo fi "
			+ "WHERE pi.type = 'th' ")
	public List<PromotionRs> selectAll();
}
