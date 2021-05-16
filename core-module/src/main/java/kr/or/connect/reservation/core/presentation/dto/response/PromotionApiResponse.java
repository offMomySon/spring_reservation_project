package kr.or.connect.reservation.core.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.core.presentation.dto.PromotionResult;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.List;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PromotionApiResponse {
    @JsonProperty("items")
    private List<PromotionResult> promotionResults;

    @Nonnull
    public static PromotionApiResponse createPromotionApiResponse(@Nonnull List<PromotionResult> promotionResults) {
        PromotionApiResponse promotionApiResponse = new PromotionApiResponse();
        promotionApiResponse.setPromotionResults(promotionResults);
        return promotionApiResponse;
    }
}
