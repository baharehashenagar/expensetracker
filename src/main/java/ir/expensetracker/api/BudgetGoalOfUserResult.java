package ir.expensetracker.api;

public class BudgetGoalOfUserResult {
    private String category;
    private Integer maxAmount;
    private String dueDate;

    public BudgetGoalOfUserResult(String category, Integer maxAmount, String dueDate) {
        this.category = category;
        this.maxAmount = maxAmount;
        this.dueDate = dueDate;
    }

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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "{" +
                "category='" + category + '\'' +
                ", maxAmount=" + maxAmount +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
