package ir.expensetracker.api;

public class CategoryDeleteParam {
    private String category;

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
