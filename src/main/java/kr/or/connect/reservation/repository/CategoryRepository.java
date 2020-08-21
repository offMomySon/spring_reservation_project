package kr.or.connect.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.or.connect.reservation.dto.CategoryRs;
import kr.or.connect.reservation.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("SELECT "
			+ "new kr.or.connect.reservation.dto.CategoryRs ( "
			+ "ca.id, "
			+ "ca.name, "
			+ "COUNT(*) "
			+ " ) "
			+ "FROM Product pr "
			+ "JOIN pr.category ca "
			+ "JOIN pr.displayInfos di "
			+ "GROUP BY ca.id, ca.name ")
	public List<CategoryRs> selectAll();
}
