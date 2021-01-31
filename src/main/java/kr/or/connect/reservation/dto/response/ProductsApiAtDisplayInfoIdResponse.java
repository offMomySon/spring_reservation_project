package kr.or.connect.reservation.dto.response;

import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.exception.list.ProductCompletableFutureExecutionException;
import kr.or.connect.reservation.exception.list.ProductCompletableFutureInterruptedExceiption;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductsApiAtDisplayInfoIdResponse {
    double averageScore;
    DisplayInfoResult displayInfo;
    DisplayInfoImageResult displayInfoImage;
    List<CommentResult> comments;
    List<ProductImageResult> productImages;
    List<ProductPriceResult> productPrices;

    public static ProductsApiAtDisplayInfoIdResponse createProductsApiAtDisplayInfoIdResponse(
            CompletableFuture<Double> averageScoreFuture,
            CompletableFuture<DisplayInfoResult> displayInfoFuture,
            CompletableFuture<DisplayInfoImageResult> displayInfoImageFuture,
            CompletableFuture<List<CommentResult>> commentsFuture,
            CompletableFuture<List<ProductImageResult>> productImagesFuture,
            CompletableFuture<List<ProductPriceResult>> productPricesFuture) {
        ProductsApiAtDisplayInfoIdResponse productsApiAtDisplayInfoIdResponse = new ProductsApiAtDisplayInfoIdResponse();

        try {
            productsApiAtDisplayInfoIdResponse.setAverageScore(averageScoreFuture.get());
            productsApiAtDisplayInfoIdResponse.setDisplayInfo(displayInfoFuture.get());
            productsApiAtDisplayInfoIdResponse.setDisplayInfoImage(displayInfoImageFuture.get());
            productsApiAtDisplayInfoIdResponse.setComments(commentsFuture.get());
            productsApiAtDisplayInfoIdResponse.setProductImages(productImagesFuture.get());
            productsApiAtDisplayInfoIdResponse.setProductPrices(productPricesFuture.get());
        } catch (InterruptedException e) {
            throw new ProductCompletableFutureInterruptedExceiption();
        } catch (ExecutionException e) {
            throw new ProductCompletableFutureExecutionException();
        }

        return productsApiAtDisplayInfoIdResponse;
    }
}