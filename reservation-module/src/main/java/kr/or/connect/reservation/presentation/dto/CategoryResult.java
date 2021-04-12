package kr.or.connect.reservation.presentation.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryResult {
    private long id;
    private String name;
    private long count;

    public CategoryResult(long id, String name, long count) {
        super();
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public static CategoryResult createCategoryResult(long id, String name, long count) {
        CategoryResult categoryResult = new CategoryResult(id, name, count);
        return categoryResult;
    }
}
