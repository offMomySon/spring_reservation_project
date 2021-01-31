package kr.or.connect.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.connect.reservation.dto.CategoryResult;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nonnull;
import java.util.List;

@Getter
@Setter
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
