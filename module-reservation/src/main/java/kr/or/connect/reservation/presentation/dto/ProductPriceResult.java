package kr.or.connect.reservation.presentation.dto;

import kr.or.connect.reservation.domain.ProductPrice;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPriceResult {
    private long productPriceId;
    private long productId;
    private String priceTypeName;
    private long price;
    private double discountRate;
    private Date createDate;
    private Date modifyDate;

    public ProductPriceResult(long productPriceId, long productId, String priceTypeName, long price, double discountRate,
                              Date createDate, Date modifyDate) {
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
