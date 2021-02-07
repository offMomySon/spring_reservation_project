package kr.or.connect.reservation.dto;

import kr.or.connect.reservation.model.DisplayInfo;
import kr.or.connect.reservation.model.FileInfo;
import kr.or.connect.reservation.model.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductResult {
    private Long displayInfoId;
    private Long productId;
    private String productDescription;
    private String placeName;
    private String productContent;
    private String productImageUrl;

    public ProductResult(Long displayInfoId, Long productId, String productDescription, String placeName,
                         String productContent, String productImageUrl) {
        super();
        this.displayInfoId = displayInfoId;
        this.productId = productId;
        this.productDescription = productDescription;
        this.placeName = placeName;
        this.productContent = productContent;
        this.productImageUrl = productImageUrl;
    }

    public static ProductResult makeProductResult(Product product, DisplayInfo displayInfo, FileInfo fileInfo) {
        ProductResult productResult = new ProductResult(
                displayInfo.getId(),
                product.getId(),
                product.getDescription(),
                displayInfo.getPlaceName(),
                product.getContent(),
                fileInfo.getSaveFileName()
        );
        return productResult;
    }

}
