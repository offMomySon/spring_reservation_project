package kr.or.connect.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.dto.ProductResult;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nonnull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductsApiResponse {
    @JsonProperty("totalCount")
    long totalCount;
    @JsonProperty("items")
    List<ProductResult> Products;

    @Nonnull
    public static ProductsApiResponse createProductsApiResponse(long totalCount, @Nonnull List<ProductResult> products) {
        ProductsApiResponse productsApiResponse = new ProductsApiResponse();
        productsApiResponse.setTotalCount(totalCount);
        productsApiResponse.setProducts(products);
        return productsApiResponse;
    }
}
