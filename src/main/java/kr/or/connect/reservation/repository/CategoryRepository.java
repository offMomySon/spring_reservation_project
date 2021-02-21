package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT count(*) FROM Category category JOIN category.products products JOIN products.displayInfos displayinfos where category.id = :id")
    long countProductDisplayInfo(@Param("id") long categoryId);
}
