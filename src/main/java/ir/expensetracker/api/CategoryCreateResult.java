package ir.expensetracker.api;

public class CategoryCreateResult {
    private Integer categoryId;

    public CategoryCreateResult(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
