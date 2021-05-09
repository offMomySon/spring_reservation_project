package kr.or.connect.reservation.core.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.core.presentation.dto.CategoryResult;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryApiResponse {
    @JsonProperty("items")
    private List<CategoryResult> categoryResults;

    @Nonnull
    public static CategoryApiResponse createCategoryApiResponse(@Nonnull List<CategoryResult> categoryResults) {
        CategoryApiResponse categoryApiResponse = new CategoryApiResponse();
        categoryApiResponse.setCategoryResults(categoryResults);
        return categoryApiResponse;
    }
}
