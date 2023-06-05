package ir.expensetracker.api;

public class AllTransactionsInMonthByCategoryParam {
    private String date;
    private String category;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
                " date='" + date + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
