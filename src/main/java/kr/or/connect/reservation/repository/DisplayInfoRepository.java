package kr.or.connect.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kr.or.connect.reservation.dto.CommentImageRs;
import kr.or.connect.reservation.dto.CommentRs;
import kr.or.connect.reservation.dto.DisplayInfoImageRs;
import kr.or.connect.reservation.dto.DisplayInfoRs;
import kr.or.connect.reservation.dto.ProductImageRs;
import kr.or.connect.reservation.dto.ProductPriceRs;
import kr.or.connect.reservation.model.DisplayInfo;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.model.ReservationInfo;

public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long> {

	@Query("SELECT "
			+ "pd "
			+ "FROM Product pd "
			+ "JOIN fetch pd.displayInfos di "
			+ "JOIN fetch pd.productPrices pp "
			+ "WHERE di.id = ?1 "
			+ "ORDER BY pp.id DESC")
	List<Product> selectProductPrice(long displayInfoId);

	@Query("SELECT "
			+ "ruc.score "
			+ "FROM Product pd "
			+ "JOIN pd.displayInfos di "
			+ "JOIN pd.reservationUserComments ruc "
			+ "WHERE di.id = ?1")
	List<Double> selectScore(long displayInfoId);

	@Nonnull
	@Query("SELECT "
			+ "ri "
			+ "FROM ReservationInfo ri "
			+ "JOIN fetch ri.userComments ruc "
			+ "JOIN fetch ruc.reservationUserCommentImages ruci "
			+ "JOIN fetch ruci.fileInfo fi "
			+ "WHERE ruc.id = ?1")
	List<ReservationInfo> selectCommentImageList(long userCommentId);
	
	@Query("SELECT "
			+ "pd "
			+ "FROM Product pd "
			+ "JOIN fetch pd.displayInfos di "
			+ "JOIN fetch pd.reservationInfos ri "
			+ "JOIN fetch ri.userComments ruc "
			+ "Where di.id = ?1 "
			+ "ORDER BY ruc.id DESC")
	List<Product> selectComment(long displayInfoId);

	@Query("SELECT "
			+ "di "
			+ "FROM DisplayInfo di "
			+ "JOIN fetch di.displayinfoImages di_img "
			+ "JOIN fetch di_img.fileInfo fi "
			+ "Where di.id = ?1")
	DisplayInfo selectDisplayInfoImage(long displayInfoId);

	@Query("SELECT "
			+ "pr "
			+ "FROM Product pr "
			+ "JOIN pr.productImages pi "
			+ "JOIN pr.displayInfos di "
			+ "JOIN pi.fileInfo fi "
			+ "Where type in ('ma', 'et') AND di.id = ?1")
	Page<Product> selectProductImageList(long displayInfoId, Pageable pageable);

	@Query("SELECT "
			+ "pr "
			+ "FROM Product pr "
			+ "JOIN fetch pr.displayInfos di "
			+ "JOIN fetch pr.category ca "
			+ "Where di.id = ?1")
	Product selectDisplayInfo(long displayInfoId);

}
