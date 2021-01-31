package kr.or.connect.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.exception.list.ProductCompletableFutureExecutionException;
import kr.or.connect.reservation.exception.list.ProductCompletableFutureInterruptedExceiption;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductsApiAtDisplayInfoIdResponse {
    @JsonProperty("averageScore")
    double averageScore;
    @JsonProperty("displayInfo")
    DisplayInfoResult displayInfo;
    @JsonProperty("displayInfoImage")
    DisplayInfoImageResult displayInfoImage;
    @JsonProperty("comments")
    List<CommentResult> comments;
    @JsonProperty("productImages")
    List<ProductImageResult> productImages;
    @JsonProperty("productPrices")
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