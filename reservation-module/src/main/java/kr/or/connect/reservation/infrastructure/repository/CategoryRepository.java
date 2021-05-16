package kr.or.connect.reservation.infrastructure.repository;

import kr.or.connect.reservation.core.presentation.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT count(*) FROM category " +
            "JOIN product on category.id = product.category_id " +
            "JOIN display_info on product.id = display_info.product_id " +
            "where category.id = :id", nativeQuery = true)
    long countProductDisplayInfo(@Param("id") long categoryId);
}
