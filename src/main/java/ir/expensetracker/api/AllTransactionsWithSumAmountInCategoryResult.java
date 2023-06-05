package ir.expensetracker.api;

public class AllTransactionsWithSumAmountInCategoryResult {
    private String category;
    private String date;
    private Integer amount;

    public AllTransactionsWithSumAmountInCategoryResult(String category, String date, Integer amount) {
        this.category = category;
        this.date = date;
        this.amount = amount;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{" +
                "category='" + category + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }
}
