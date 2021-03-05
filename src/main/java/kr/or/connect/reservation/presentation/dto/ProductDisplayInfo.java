package kr.or.connect.reservation.presentation.dto;

import kr.or.connect.reservation.domain.DisplayInfo;
import kr.or.connect.reservation.domain.FileInfo;
import kr.or.connect.reservation.domain.Product;
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

    public static ProductDisplayInfo makeProductResult(Product product, DisplayInfo displayInfo, FileInfo fileInfo) {
        ProductDisplayInfo productDisplayInfo = new ProductDisplayInfo(
                displayInfo.getId(),
                product.getId(),
                product.getDescription(),
                displayInfo.getPlaceName(),
                product.getContent(),
                fileInfo.getSaveFileName()
        );
        return productDisplayInfo;
    }
}
