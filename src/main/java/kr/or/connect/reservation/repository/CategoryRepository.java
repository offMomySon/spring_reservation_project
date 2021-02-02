package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.Nonnull;
import javax.persistence.Tuple;
import java.util.List;

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
