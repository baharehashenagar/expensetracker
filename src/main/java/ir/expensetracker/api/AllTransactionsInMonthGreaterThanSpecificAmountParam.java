package ir.expensetracker.api;

public class AllTransactionsInMonthGreaterThanSpecificAmountParam {
    private Integer userId;
    private String date;
    private Integer amount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
                "userId=" + userId +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }
}
