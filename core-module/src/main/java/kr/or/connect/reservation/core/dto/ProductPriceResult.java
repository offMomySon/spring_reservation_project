package kr.or.connect.reservation.core.dto;

import kr.or.connect.reservation.core.domain.ProductPrice;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPriceResult {
    private long productPriceId;
    private long productId;
    private String priceTypeName;
    private long price;
    private double discountRate;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public ProductPriceResult(long productPriceId, long productId, String priceTypeName, long price, double discountRate,
                              LocalDateTime createDate, LocalDateTime modifyDate) {
        super();
        this.productPriceId = productPriceId;
        this.productId = productId;
        this.priceTypeName = priceTypeName;
        this.price = price;
        this.discountRate = discountRate;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public static ProductPriceResult makeProductPriceResult(ProductPrice productPrice) {
        return new ProductPriceResult
                (productPrice.getId(), productPrice.getProduct().getId(), productPrice.getPriceTypeName(),
                        productPrice.getPrice(), productPrice.getDiscountRate(),
                        productPrice.getCreateDate(), productPrice.getModifyDate());
    }
}
