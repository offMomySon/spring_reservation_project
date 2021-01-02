package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.Nonnull;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

	@Nonnull
	@Query("SELECT "
			+ "pd "
			+ "FROM Product pd "
			+ "JOIN  pd.promotions pm "
			+ "JOIN  pd.productImages pi "
			+ "JOIN  pi.fileInfo fi "
			+ "where pi.type = 'th' ")
	public List<Product> selectAll();
}
