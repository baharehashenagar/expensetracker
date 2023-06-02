package ir.expensetracker.api;

public class BudgetGoalOfUserResult {
    private String category;
    private Integer maxAmount;

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
}
