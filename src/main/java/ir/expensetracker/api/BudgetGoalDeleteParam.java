package ir.expensetracker.api;

public class BudgetGoalDeleteParam {
    private Integer budgetGoalId;

    public BudgetGoalDeleteParam(Integer budgetGoalId) {
        this.budgetGoalId = budgetGoalId;
    }

    public Integer getBudgetGoalId() {
        return budgetGoalId;
    }

    public void setBudgetGoalId(Integer budgetGoalId) {
        this.budgetGoalId = budgetGoalId;
    }
}
