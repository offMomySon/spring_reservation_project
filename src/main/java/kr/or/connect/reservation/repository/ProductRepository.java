package kr.or.connect.reservation.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//		@Query("SELECT pr FROM Product pr"
//				+ "JOIN pr.category ca"
//				+ "JOIN pr.displayInfos di "
//				+ "JOIN pr.productImages pi "
//				+ "JOIN pi.fileInfo fi"
//				+ "WHERE ca.id = :categoryId AND pi.type = 'th' LIMIT :start, :end")
//		List<Product> findProductByCategoryId(@Param("categoryId") Long categoryId, @Param("start") Long start, 
//				@Param("end") Long end);
//
//		@Query("SELECT pr FROM Product pr "
//				+ "JOIN pr.category ca "
//				+ "JOIN pr.displayInfos di "
//				+ "JOIN pr.productImages pi "
//				+ "JOIN pi.fileInfo fi "
//				+ "WHERE pi.type = 'th'")
//		List<Product> findAll();
		
		@Query("SELECT pr FROM Product pr JOIN pr.category ca Where ca.id = ?1")
		@Transactional
		List<Product> findAllbyId(Long cat_id);
		
		
//		@Query("SELECT pr FROM Product pr "
//				+ "JOIN pr.category ca "
//				+ "JOIN pr.displayInfos di "
//				+ "JOIN pr.productImages pi "
//				+ "JOIN pi.fileInfo fi")
//		List<Product> findAll();
}
