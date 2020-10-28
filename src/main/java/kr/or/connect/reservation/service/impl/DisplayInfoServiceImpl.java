package kr.or.connect.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dto.CommentRs;
import kr.or.connect.reservation.controller.ReservationApiController;
import kr.or.connect.reservation.dto.CommentImageRs;
import kr.or.connect.reservation.dto.DisplayInfoRs;
import kr.or.connect.reservation.dto.DisplayInfoImageRs;
import kr.or.connect.reservation.dto.ProductImageRs;
import kr.or.connect.reservation.dto.ProductPriceRs;
import kr.or.connect.reservation.exception.DisplayInfoIdNotExistExceiption;
import kr.or.connect.reservation.model.Category;
import kr.or.connect.reservation.model.DisplayInfo;
import kr.or.connect.reservation.model.DisplayInfoImage;
import kr.or.connect.reservation.model.FileInfo;
import kr.or.connect.reservation.model.Product;
import kr.or.connect.reservation.model.ProductImage;
import kr.or.connect.reservation.model.ProductPrice;
import kr.or.connect.reservation.model.ReservationInfo;
import kr.or.connect.reservation.model.ReservationUserComment;
import kr.or.connect.reservation.model.ReservationUserCommentImage;
import kr.or.connect.reservation.repository.DisplayInfoRepository;
import kr.or.connect.reservation.service.DisplayInfoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	private DisplayInfoRepository displayInfoRep;

	@Nonnull
	@Transactional
	@Override
	public DisplayInfoRs getDisplayInfo(long displayInfoId) {
		log.debug("GET. displayInfoId = {}.", displayInfoId);
		
		Product product = displayInfoRep.selectDisplayInfo(displayInfoId);
		
		return makeDisplayInfoRs(product);
	}
	
	private DisplayInfoRs makeDisplayInfoRs(Product product) {
		
		Category category = product.getCategory();
		DisplayInfo displayInfo = product.getDisplayInfos().stream().findFirst().get();
		
		return new DisplayInfoRs
				(product.getId(),category.getId(),displayInfo.getId(),
						category.getName(),product.getDescription(),product.getContent(),
						product.getEvent(),displayInfo.getPlaceName(),displayInfo.getPlaceLot(),
						displayInfo.getPlaceStreet(),displayInfo.getTel(),displayInfo.getHomepage(),displayInfo.getEmail(),
						displayInfo.getCreateDate(),displayInfo.getModifyDate(),displayInfo.getOpeningHours());
	}

	@Nonnull
	@Transactional
	@Override
	public List<ProductImageRs> getProductImageList(long displayInfoId) {
		PageRequest pageRequest =  PageRequest.of(FIRST_PAGE, (int) SELECT_IMAGE_COUNT_LIMIT);
		
		List<Product> productList = displayInfoRep.selectProductImageList(displayInfoId, pageRequest).getContent();
		List<ProductImageRs> productImageRsList = new ArrayList();
		
		for(Product product : productList) {
			productImageRsList.add(makeProductImageRs(product));
		}
		
		return productImageRsList;
	}

	private ProductImageRs makeProductImageRs(Product product) {
		
		ProductImage productImage = product.getProductImages().stream().findFirst().get();
		FileInfo fileInfo = productImage.getFileInfo();
		
		return new ProductImageRs(
				product.getId(),
				productImage.getId(),
				productImage.getType(),
				fileInfo.getId(), fileInfo.getFileName(), fileInfo.getSaveFileName(),fileInfo.getContentType(), 
				fileInfo.getDeleteFlag(), fileInfo.getCreateDate(), fileInfo.getModifyDate());
	}
	
	@Nonnull
	@Transactional
	@Override
	public DisplayInfoImageRs getDisplayInfoImage(long displayInfoId) {
		return makeDisplayInfoImageRs(displayInfoRep.selectDisplayInfoImage(displayInfoId));
	}

	private DisplayInfoImageRs makeDisplayInfoImageRs(DisplayInfo displayInfo) {
		
		DisplayInfoImage displayInfoImage = displayInfo.getDisplayinfoImages().stream().findFirst().get();
		FileInfo fileInfo = displayInfoImage.getFileInfo();
		return new DisplayInfoImageRs(displayInfoImage.getId(),displayInfo.getId(), 
						fileInfo.getId(), fileInfo.getFileName(), fileInfo.getSaveFileName(), fileInfo.getContentType(), 
						fileInfo.getDeleteFlag(), fileInfo.getCreateDate(), fileInfo.getModifyDate());
	}
	
	@Nonnull
	@Transactional
	@Override
	public List<CommentRs> getCommentList(long displayInfoId) {
		List<Product> selectedCommentProduct = displayInfoRep.selectComment(displayInfoId);
		List<CommentRs> commentList = new ArrayList();
		
		for(Product product : selectedCommentProduct) {
			commentList.add(makeCommentRs(product));
		}
		
		for (CommentRs commentRs : commentList) {
			List<ReservationInfo> rsvInfoList = displayInfoRep.selectCommentImageList(commentRs.getCommentId());
			
			List<CommentImageRs> commentImageRs = new ArrayList();
			for(ReservationInfo rsvInfo : rsvInfoList) {
				commentImageRs.add(makeCommentImageRs(rsvInfo));
			}
			
			commentRs.setCommentImages(commentImageRs);
		}

		return commentList;
	}

	private CommentRs makeCommentRs(Product product) {
		ReservationInfo rsvInfo = product.getReservationInfos().stream().findFirst().get();
		ReservationUserComment rsvUserComment = rsvInfo.getUserComments().stream().findFirst().get();
		
		return new CommentRs(rsvUserComment.getId(), product.getId(), rsvInfo.getId(), 
				rsvUserComment.getScore(), rsvUserComment.getComment(), 
				rsvInfo.getReservationName(), rsvInfo.getReservationTel(), rsvInfo.getReservationEmail(), rsvInfo.getReservationDate(),
				rsvUserComment.getCreateDate(), rsvUserComment.getModifyDate());
	}
	
	private CommentImageRs makeCommentImageRs(ReservationInfo rsvInfo) {
		ReservationUserComment rsvUserComment = rsvInfo.getUserComments().stream().findFirst().get();
		ReservationUserCommentImage rsvUserCommentImg = rsvUserComment.getReservationUserCommentImages().stream().findFirst().get();
		FileInfo fileInfo = rsvUserCommentImg.getFileInfo();
		
		return new CommentImageRs(
				rsvUserCommentImg.getId(),
				rsvInfo.getId(), 
				rsvUserComment.getId(),
				fileInfo.getId(),
				fileInfo.getFileName(),
				fileInfo.getSaveFileName(),
				fileInfo.getContentType(),
				fileInfo.getDeleteFlag(),
				fileInfo.getCreateDate(),
				fileInfo.getModifyDate());
	}
	
	
	
	@Nonnull
	@Override
	public double getAverageScore(long displayInfoId) {
		double scoreSum = 0;

		List<Double> scoreList = displayInfoRep.selectScore(displayInfoId);
		for (Double score : scoreList) {
			scoreSum += score;
		}

		if (scoreList.size() == 0)
			return 0;
		return scoreSum / scoreList.size();
	}

	@Nonnull
	@Override
	public List<ProductPriceRs> getProductPriceList(long displayInfoId) {
		List<Product> productList = displayInfoRep.selectProductPrice(displayInfoId);
		List<ProductPriceRs> productPriceList = new ArrayList();
		
		for(Product product : productList) {
			productPriceList.add(makeProductPriceRs(product));
		}
		
		return productPriceList;
	}

	private ProductPriceRs makeProductPriceRs(Product product) {
		DisplayInfo displayInfo  = product.getDisplayInfos().stream().findFirst().get();
		ProductPrice productPrice = product.getProductPrices().stream().findFirst().get();
		
		return new ProductPriceRs
				(productPrice.getId(),product.getId(),productPrice.getPriceTypeName(),
						productPrice.getPrice(), productPrice.getDiscountRate(),
						productPrice.getCreateDate(),productPrice.getModifyDate());
	}	
}
