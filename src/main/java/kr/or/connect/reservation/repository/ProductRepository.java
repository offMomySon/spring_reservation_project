package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT "
            + "pr "
            + "FROM Product pr "
            + "JOIN pr.category ca "
            + "JOIN pr.displayInfos di "
            + "JOIN pr.productImages pi "
            + "JOIN pi.fileInfo fi "
            + "WHERE pi.type = 'th' ")
    Page<Product> selectWithTypeTH(Pageable pageable);

    @Query("SELECT "
            + "pr "
            + "FROM Product pr "
            + "JOIN pr.category ca "
            + "JOIN pr.displayInfos di "
            + "JOIN pr.productImages pi "
            + "JOIN pi.fileInfo fi "
            + "WHERE pi.type = 'th' and "
            + "ca.id = ?1")
    Page<Product> selectWithCategoryId(Long categoryId, Pageable pageable);


    @Query("SELECT count(*) FROM Product pr "
            + "JOIN pr.displayInfos di "
            + "JOIN pr.category ca "
            + "WHERE ca.id = ?1")
    long countWithCategoryId(Long categoryId);
//
//    Page<Product> findAll(Pageable pageable)
}
