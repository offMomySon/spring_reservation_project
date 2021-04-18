package kr.or.connect.reservation.core.dto;

import kr.or.connect.reservation.core.domain.Category;
import kr.or.connect.reservation.core.domain.DisplayInfo;
import kr.or.connect.reservation.core.domain.Product;
import kr.or.connect.reservation.core.domain.ReservationInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DisplayInfoResult {
    private long productId;
    private long categoryId;
    private long displayInfoId;
    private String categoryName;
    private String productDescription;
    private String productContent;
    private String productEvent;
    private String placeName;
    private String placeLot;
    private String placeStreet;
    private String telephone;
    private String homepage;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String openingHours;

    public DisplayInfoResult(long productId, long categoryId, long displayInfoId, String categoryName,
                             String productDescription, String productContent, String productEvent, String placeName, String placeLot,
                             String placeStreet, String telephone, String homepage, String email, LocalDateTime createDate, LocalDateTime modifyDate,
                             String openingHours) {
        super();
        this.productId = productId;
        this.categoryId = categoryId;
        this.displayInfoId = displayInfoId;
        this.categoryName = categoryName;
        this.productDescription = productDescription;
        this.productContent = productContent;
        this.productEvent = productEvent;
        this.placeName = placeName;
        this.placeLot = placeLot;
        this.placeStreet = placeStreet;
        this.telephone = telephone;
        this.homepage = homepage;
        this.email = email;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.openingHours = openingHours;
    }

    public static DisplayInfoResult makeDisplayInfoResult(@Nonnull ReservationInfo reservationInfo) {
        Product product = reservationInfo.getProduct();
        DisplayInfo displayInfo = reservationInfo.getDisplayInfo();
        Category category = product.getCategory();

        return new DisplayInfoResult(
                product.getId(), category.getId(), displayInfo.getId(),
                category.getName(), product.getDescription(), product.getContent(),
                product.getEvent(), displayInfo.getPlaceName(), displayInfo.getPlaceLot(),
                displayInfo.getPlaceStreet(), displayInfo.getTel(), displayInfo.getHomepage(), displayInfo.getEmail(),
                displayInfo.getCreateDate(), displayInfo.getModifyDate(), displayInfo.getOpeningHoursComment());
    }

    public static DisplayInfoResult makeDisplayInfoResult(DisplayInfo displayInfo, Product product, Category category) {
        return new DisplayInfoResult(
                product.getId(), category.getId(), displayInfo.getId(),
                category.getName(), product.getDescription(), product.getContent(),
                product.getEvent(), displayInfo.getPlaceName(), displayInfo.getPlaceLot(),
                displayInfo.getPlaceStreet(), displayInfo.getTel(), displayInfo.getHomepage(), displayInfo.getEmail(),
                displayInfo.getCreateDate(), displayInfo.getModifyDate(), displayInfo.getOpeningHoursComment());
    }
}
