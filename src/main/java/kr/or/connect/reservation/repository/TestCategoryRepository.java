package kr.or.connect.reservation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.or.connect.reservation.model.Category;

public interface TestCategoryRepository extends JpaRepository<Category, Long> {
	
//	Optional<CategoryRs> findById(Long id);
	Category findByName(String name);
	
}
