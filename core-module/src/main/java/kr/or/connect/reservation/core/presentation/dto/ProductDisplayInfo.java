package kr.or.connect.reservation.core.presentation.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDisplayInfo {
    private Long displayInfoId;
    private Long productId;
    private String productDescription;
    private String placeName;
    private String productContent;
    private String productImageUrl;

    public ProductDisplayInfo(Long displayInfoId, Long productId, String productDescription, String placeName,
                              String productContent, String productImageUrl) {
        super();
        this.displayInfoId = displayInfoId;
        this.productId = productId;
        this.productDescription = productDescription;
        this.placeName = placeName;
        this.productContent = productContent;
        this.productImageUrl = productImageUrl;
    }

    public static ProductDisplayInfo makeProductResult(Long displayInfoId, Long productId, String productDescription, String placeName, String productContent, String productImageUrl) {
        ProductDisplayInfo productDisplayInfo = new ProductDisplayInfo(
                displayInfoId,
                productId,
                productDescription,
                placeName,
                productContent,
                productImageUrl
        );
        return productDisplayInfo;
    }
}
