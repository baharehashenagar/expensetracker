package ir.expensetracker.api;

public class AllTransactionsWithSumAmountInCategoryResult {
    private String category;
    private String date;
    private Integer totalAmount;

    public AllTransactionsWithSumAmountInCategoryResult(String category, String date, Integer totalAmount) {
        this.category = category;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "{" +
                "category='" + category + '\'' +
                ", date='" + date + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
