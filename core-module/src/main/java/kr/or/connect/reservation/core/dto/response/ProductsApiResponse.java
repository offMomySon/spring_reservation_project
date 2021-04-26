package kr.or.connect.reservation.core.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.core.dto.ProductDisplayInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductsApiResponse {
    long totalCount;
    @JsonProperty("items")
    List<ProductDisplayInfo> Products;

    @Nonnull
    public static ProductsApiResponse createProductsApiResponse(long totalCount, @Nonnull List<ProductDisplayInfo> products) {
        ProductsApiResponse productsApiResponse = new ProductsApiResponse();
        productsApiResponse.setTotalCount(totalCount);
        productsApiResponse.setProducts(products);
        return productsApiResponse;
    }
}
