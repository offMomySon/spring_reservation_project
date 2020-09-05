package kr.or.connect.reservation.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.persistence.Tuple;

import org.javatuples.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.or.connect.reservation.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Nonnull
	@Query("SELECT "
			+ "ca, COUNT(*) "
			+ "FROM Product pr "
			+ "JOIN pr.category ca "
			+ "JOIN pr.displayInfos di "
			+ "GROUP BY ca.id, ca.name ")
	public List<Tuple> selectAll();
}
