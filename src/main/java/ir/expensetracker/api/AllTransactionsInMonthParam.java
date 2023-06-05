package ir.expensetracker.api;

public class AllTransactionsInMonthParam {
    private String date;

    public AllTransactionsInMonthParam(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "date='" + date + '\'' +
                '}';
    }
}
