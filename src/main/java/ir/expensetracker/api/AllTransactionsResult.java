package ir.expensetracker.api;

public class AllTransactionsResult {
    private Integer transactionId;
    private String category;
    private String description;
    private String date;
    private Integer amount;

    public AllTransactionsResult(Integer transactionId, String category, String description, String date, Integer amount) {
        this.transactionId = transactionId;
        this.category = category;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                "transactionId=" + transactionId +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }
}
