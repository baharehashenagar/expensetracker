package ir.expensetracker.service;

import ir.expensetracker.api.*;
import ir.expensetracker.entity.BudgetGoalEntity;
import ir.expensetracker.entity.CategoryEntity;
import ir.expensetracker.entity.UserEntity;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.repository.IBudgetGoalRepository;
import ir.expensetracker.service.facade.IBudgetGoalService;
import ir.expensetracker.service.facade.IValidatorService;
import ir.expensetracker.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetGoalService implements IBudgetGoalService {

    private IBudgetGoalRepository budgetGoalRepository;
    private IValidatorService validatorService;

    @Autowired
    public BudgetGoalService(IBudgetGoalRepository budgetGoalRepository, IValidatorService validatorService){
        this.budgetGoalRepository=budgetGoalRepository;
        this.validatorService=validatorService;
    }

    @Override
    public BudgetGoalCreateResult createBudgetGoal(BudgetGoalCreateParam param) {
        UserEntity user=validatorService.validateUserExistence(param.getUserId());
        CategoryEntity category=validatorService.validateCategoryExistence(param.getCategory());
        List<BudgetGoalEntity> result=budgetGoalRepository.findUserBudgetGoalForSpecificCategory(param.getUserId(),category.getId());
        if(result!=null && result.size()>0){
            throw new InvalidParameterException("Duplicate BudgetGoal");
        }
        BudgetGoalEntity budgetGoal=new BudgetGoalEntity();
        budgetGoal.setUser(user);
        budgetGoal.setCategory(category);
        budgetGoal.setBudgetDate(DateUtil.getDate(param.getDate()));
        budgetGoal.setMaxAmount(param.getMaxAmount()!=null?param.getMaxAmount():0);
        budgetGoal=budgetGoalRepository.save(budgetGoal);
        return new BudgetGoalCreateResult(budgetGoal.getId());
    }

    @Override
    public BudgetGoalDeleteResult deleteBudgetGoal(BudgetGoalDeleteParam param) {
        return null;
    }

    @Override
    public BudgetGoalOfUserResult findUserBudgetGoals(BudgetGoalOfUserParam param) {
        return null;
    }
}
