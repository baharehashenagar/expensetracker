package ir.expensetracker.api;

public class CategoryCreateParam {
    private String category;

    public CategoryCreateParam() {
    }

    public CategoryCreateParam(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
                "category='" + category + '\'' +
                '}';
    }
}
