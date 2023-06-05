package ir.expensetracker.api;

public class BudgetGoalOfUserResult {
    private Integer budgetGoalId;
    private String category;
    private Integer maxAmount;
    private String fromDate;
    private String toDate;

    public BudgetGoalOfUserResult(Integer budgetGoalId, String category, Integer maxAmount, String fromDate, String toDate) {
        this.budgetGoalId = budgetGoalId;
        this.category = category;
        this.maxAmount = maxAmount;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Integer getBudgetGoalId() {
        return budgetGoalId;
    }

    public void setBudgetGoalId(Integer budgetGoalId) {
        this.budgetGoalId = budgetGoalId;
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "{" +
                "budgetGoalId=" + budgetGoalId +
                ", category='" + category + '\'' +
                ", maxAmount=" + maxAmount +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
