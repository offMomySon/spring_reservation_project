package kr.or.connect.reservation.core.presentation.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDisplayInfoResult {
    List<ProductDisplayInfo> productDisplayInfos;
    private long productDisplaySize;

    public static ProductDisplayInfoResult craeteProductDisplayInfoResult(long productDisplaySize, @Nonnull List<ProductDisplayInfo> productDisplayInfos) {
        ProductDisplayInfoResult productDisplayInfoResult = new ProductDisplayInfoResult();
        productDisplayInfoResult.setProductDisplayInfos(productDisplayInfos);
        productDisplayInfoResult.setProductDisplaySize(productDisplaySize);

        return productDisplayInfoResult;
    }
}
