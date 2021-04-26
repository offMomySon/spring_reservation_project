package kr.or.connect.reservation.core.dto.response;

import kr.or.connect.reservation.core.dto.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductsApiAtDisplayInfoIdResponse {
    double averageScore;
    DisplayInfoResult displayInfo;
    DisplayInfoImageResult displayInfoImage;
    List<CommentResult> comments;
    List<ProductImageResult> productImages;
    List<ProductPriceResult> productPrices;

    public ProductsApiAtDisplayInfoIdResponse(double averageScore, DisplayInfoResult displayInfo, DisplayInfoImageResult displayInfoImage, List<CommentResult> comments, List<ProductImageResult> productImages, List<ProductPriceResult> productPrices) {
        this.averageScore = averageScore;
        this.displayInfo = displayInfo;
        this.displayInfoImage = displayInfoImage;
        this.comments = comments;
        this.productImages = productImages;
        this.productPrices = productPrices;
    }

    public static ProductsApiAtDisplayInfoIdResponse makeProductsApiAtDisplayInfoIdResponse(double averageScore, DisplayInfoResult displayInfo, DisplayInfoImageResult displayInfoImage, List<CommentResult> comments, List<ProductImageResult> productImages, List<ProductPriceResult> productPrices) {
        ProductsApiAtDisplayInfoIdResponse productsApiAtDisplayInfoIdResponse = new ProductsApiAtDisplayInfoIdResponse(
                averageScore,
                displayInfo,
                displayInfoImage,
                comments,
                productImages,
                productPrices
        );

        return productsApiAtDisplayInfoIdResponse;
    }

    public static ProductsApiAtDisplayInfoIdResponse makeDummyProductsApiAtDisplayInfoIdResponse() {
        ProductsApiAtDisplayInfoIdResponse productsApiAtDisplayInfoIdResponse = new ProductsApiAtDisplayInfoIdResponse(
                0,
                null,
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        return productsApiAtDisplayInfoIdResponse;
    }

}