package ir.expensetracker.api;

public class AllTransactionsInMonthGreaterThanSpecificAmountParam {
    private String date;
    private Integer amount;

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
                " date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }
}
