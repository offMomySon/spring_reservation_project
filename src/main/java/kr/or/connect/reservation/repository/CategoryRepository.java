package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
