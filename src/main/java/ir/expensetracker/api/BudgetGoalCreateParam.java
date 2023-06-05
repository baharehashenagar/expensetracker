package ir.expensetracker.api;

public class BudgetGoalCreateParam {
    private String category;
    private Integer maxAmount;
    private String date;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
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
                "category='" + category + '\'' +
                ", maxAmount=" + maxAmount +
                ", date='" + date + '\'' +
                '}';
    }
}
