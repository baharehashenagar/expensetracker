package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;

public interface IBudgetGoalService {

    public BudgetGoalCreateResult createBudgetGoal(BudgetGoalCreateParam param);
    public BudgetGoalDeleteResult deleteBudgetGoal(BudgetGoalDeleteParam param);
    public BudgetGoalOfUserResult findUserBudgetGoals(BudgetGoalOfUserParam param);
}
