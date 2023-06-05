package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;

import java.util.List;

public interface IBudgetGoalService {

    public BudgetGoalCreateResult createBudgetGoal(BudgetGoalCreateParam param, String jwt);

    public BudgetGoalDeleteResult deleteBudgetGoal(BudgetGoalDeleteParam param, String jwt);

    public List<BudgetGoalOfUserResult> findUserBudgetGoals(BudgetGoalOfUserParam param, String jwt);
}
