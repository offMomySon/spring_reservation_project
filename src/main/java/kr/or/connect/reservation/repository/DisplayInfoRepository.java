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

public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long> {

	@Query("SELECT "
			+ "new kr.or.connect.reservation.dto.ProductPriceRs ( "
			+ "pp.id, "
			+ "pd.id, "
			+ "pp.priceTypeName, "
			+ "pp.price, "
			+ "pp.discountRate, "
			+ "pp.createDate, "
			+ "pp.modifyDate "
			+ " ) "
			+ "FROM Product pd "
			+ "JOIN pd.displayInfos di "
			+ "JOIN pd.productPrices pp "
			+ "WHERE di.id = ?1 "
			+ "ORDER BY pp.id DESC")
	List<ProductPriceRs> selectProductPrice(long displayInfoId);

	@Query("SELECT "
			+ "ruc.score "
			+ "FROM Product pd "
			+ "JOIN pd.displayInfos di "
			+ "JOIN pd.reservationUserComments ruc "
			+ "WHERE di.id = ?1")
	List<Double> selectScore(long displayInfoId);

	@Nonnull
	@Query("SELECT "
			+ "new kr.or.connect.reservation.dto.CommentImageRs ( "
			+ "ruci.id, "
			+ "ri.id, "
			+ "ruc.id, "
			+ "fi.id, "
			+ "fi.fileName, "
			+ "fi.saveFileName, "
			+ "fi.contentType, "
			+ "fi.deleteFlag, "
			+ "fi.createDate, "
			+ "fi.modifyDate"
			+ ") "
			+ "FROM ReservationInfo ri "
			+ "JOIN ri.userComments ruc "
			+ "JOIN ruc.reservationUserCommentImages ruci "
			+ "JOIN ruci.fileInfo fi "
			+ "WHERE ruc.id = ?1")
	List<CommentImageRs> selectCommentImageList(long userCommentId);
	
	@Query("SELECT "
			+ "new kr.or.connect.reservation.dto.CommentRs "
			+ "( "
			+ "ruc.id, "
			+ "pd.id, "
			+ "ri.id, "
			+ "ruc.score, "
			+ "ruc.comment, "
			+ "ri.reservationName, "
			+ "ri.reservationTel, "
			+ "ri.reservationEmail, "
			+ "ri.reservationDate, "
			+ "ruc.createDate, "
			+ "ruc.modifyDate "
			+ ") "
			+ "FROM Product pd "
			+ "JOIN pd.displayInfos di "
			+ "JOIN pd.reservationInfos ri "
			+ "JOIN ri.userComments ruc "
			+ "Where di.id = ?1 "
			+ "ORDER BY ruc.id DESC")
	List<CommentRs> selectComment(long displayInfoId);

	@Query("SELECT "
			+ "new kr.or.connect.reservation.dto.DisplayInfoImageRs "
			+ "( "
			+ "di_img.id, "
			+ "di.id, "
			+ "fi.id, "
			+ "fi.fileName, "
			+ "fi.saveFileName, "
			+ "fi.contentType, "
			+ "fi.deleteFlag, "
			+ "fi.createDate, "
			+ "fi.modifyDate "
			+ ") "
			+ "FROM DisplayInfo di "
			+ "JOIN di.displayinfoImages di_img "
			+ "JOIN di_img.fileInfo fi "
			+ "Where di.id = ?1")
	DisplayInfoImageRs selectDisplayInfoImage(long displayInfoId);

	@Query("SELECT "
			+ "new kr.or.connect.reservation.dto.ProductImageRs "
			+ "( "
			+ "pr.id, "
			+ "pi.id, "
			+ "pi.type, "
			+ "fi.id, "
			+ "fi.fileName, "
			+ "fi.saveFileName, "
			+ "fi.contentType, "
			+ "fi.deleteFlag, "
			+ "fi.createDate, "
			+ "fi.modifyDate "
			+ ") "
			+ "FROM Product pr "
			+ "JOIN pr.productImages pi "
			+ "JOIN pr.displayInfos di "
			+ "JOIN pi.fileInfo fi "
			+ "Where type in ('ma', 'et') AND di.id = ?1")
	Page<ProductImageRs> selectProductImageList(long displayInfoId, Pageable pageable);

	@Query("SELECT "
			+ "new kr.or.connect.reservation.dto.DisplayInfoRs "
			+ "( "
			+ "pr.id, "
			+ "ca.id, "
			+ "di.id, "
			+ "ca.name, "
			+ "pr.description, "
			+ "pr.content, "
			+ "pr.event, "
			+ "di.placeName, "
			+ "di.placeLot, "
			+ "di.placeStreet, "
			+ "di.tel, "
			+ "di.homepage, "
			+ "di.email, "
			+ "di.createDate, "
			+ "di.modifyDate, "
			+ "di.openingHours "
			+ ") "
			+ "FROM Product pr "
			+ "JOIN pr.displayInfos di "
			+ "JOIN pr.category ca "
			+ "Where di.id = ?1")
	DisplayInfoRs selectDisplayInfo(long displayInfoId);

}
