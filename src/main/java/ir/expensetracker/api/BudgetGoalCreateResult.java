package ir.expensetracker.api;

public class BudgetGoalCreateResult {
    private Integer budgetGoalId;

    public BudgetGoalCreateResult(Integer budgetGoalId) {
        this.budgetGoalId = budgetGoalId;
    }

    public Integer getBudgetGoalId() {
        return budgetGoalId;
    }

    public void setBudgetGoalId(Integer budgetGoalId) {
        this.budgetGoalId = budgetGoalId;
    }
}
