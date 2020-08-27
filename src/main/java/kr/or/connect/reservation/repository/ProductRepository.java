package kr.or.connect.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.annotation.Nonnull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.or.connect.reservation.dto.ProductRs;
import kr.or.connect.reservation.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
		
		@Nonnull
		@Query("SELECT "
				+ "new kr.or.connect.reservation.dto.ProductRs "
				+ "( "
				+ "di.id, "
				+ "pr.id, "
				+ "pr.description, "
				+ "di.placeName, "
				+ "pr.content, "
				+ "fi.saveFileName "
				+ " ) "
				+ "FROM Product pr "
				+ "JOIN pr.category ca "
				+ "JOIN pr.displayInfos di "
				+ "JOIN pr.productImages pi "
				+ "JOIN pi.fileInfo fi "
				+ "WHERE pi.type = 'th' ")
		Page<ProductRs> selectWithTypeTH(Pageable pageable);
		
		@Nonnull
		@Query("SELECT "
				+ "new kr.or.connect.reservation.dto.ProductRs "
				+ "( "
				+ "di.id, "
				+ "pr.id, "
				+ "pr.description, "
				+ "di.placeName, "
				+ "pr.content, "
				+ "fi.saveFileName "
				+ " ) "
				+ "FROM Product pr "
				+ "JOIN pr.category ca "
				+ "JOIN pr.displayInfos di "
				+ "JOIN pr.productImages pi "
				+ "JOIN pi.fileInfo fi "
				+ "WHERE pi.type = 'th' and "
				+ "ca.id = ?1")
		Page<ProductRs> selectWithCategoryId(Long categoryId, Pageable pageable);

		@Query("SELECT count(*) FROM Product pr JOIN pr.displayInfos di")
		long countWithDisplayInfo();
		
		@Query("SELECT count(*) FROM Product pr "
				+ "JOIN pr.displayInfos di "
				+ "JOIN pr.category ca "
				+ "WHERE ca.id = ?1")
		long countWithCategoryId(Long categoryId);
}
