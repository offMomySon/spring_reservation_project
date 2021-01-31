package kr.or.connect.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.dto.PromotionResult;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nonnull;
import java.util.List;


@Getter
@Setter
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
