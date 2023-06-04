package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;
import java.util.List;

public interface IBudgetGoalService {

    public BudgetGoalCreateResult createBudgetGoal(BudgetGoalCreateParam param);
    public BudgetGoalDeleteResult deleteBudgetGoal(BudgetGoalDeleteParam param);
    public List<BudgetGoalOfUserResult> findUserBudgetGoals(BudgetGoalOfUserParam param);
}
