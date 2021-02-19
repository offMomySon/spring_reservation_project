package kr.or.connect.reservation.repository;

import kr.or.connect.reservation.model.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

    @Query("SELECT productPrice " +
            "FROM ProductPrice productPrice " +
            "WHERE productPrice.product.id = :productId")
    List<ProductPrice> findByProductId(@Param("productId") long productId);
}
