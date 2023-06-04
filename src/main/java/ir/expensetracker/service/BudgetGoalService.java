package ir.expensetracker.service;

import ir.expensetracker.api.*;
import ir.expensetracker.entity.BudgetGoalEntity;
import ir.expensetracker.entity.CategoryEntity;
import ir.expensetracker.entity.UserEntity;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.exception.RecordNotFoundException;
import ir.expensetracker.repository.IBudgetGoalRepository;
import ir.expensetracker.service.facade.IBudgetGoalService;
import ir.expensetracker.service.facade.IValidatorService;
import ir.expensetracker.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BudgetGoalService implements IBudgetGoalService {

    private IBudgetGoalRepository budgetGoalRepository;
    private IValidatorService validatorService;

    @Autowired
    public BudgetGoalService(IBudgetGoalRepository budgetGoalRepository, IValidatorService validatorService) {
        this.budgetGoalRepository = budgetGoalRepository;
        this.validatorService = validatorService;
    }

    @Override
    public BudgetGoalCreateResult createBudgetGoal(BudgetGoalCreateParam param) {
        UserEntity user = validatorService.validateUserExistence(param.getUserId());
        CategoryEntity category = validatorService.validateCategoryExistence(param.getCategory());
        List<BudgetGoalEntity> result = budgetGoalRepository.findUserBudgetGoalForSpecificCategory(param.getUserId(), category.getId());
        if (result != null && result.size() > 0) {
            throw new InvalidParameterException("Duplicate BudgetGoal");
        }
        BudgetGoalEntity budgetGoal = new BudgetGoalEntity();
        budgetGoal.setUser(user);
        budgetGoal.setCategory(category);
        budgetGoal.setBudgetDate(DateUtil.getDate(param.getDate()));
        budgetGoal.setMaxAmount(param.getMaxAmount() != null ? param.getMaxAmount() : 0);
        budgetGoal = budgetGoalRepository.save(budgetGoal);
        return new BudgetGoalCreateResult(budgetGoal.getId());
    }

    @Override
    public BudgetGoalDeleteResult deleteBudgetGoal(BudgetGoalDeleteParam param) {
        Optional<BudgetGoalEntity> budgetGoal = budgetGoalRepository.findById(param.getBudgetGoalId());
        if (budgetGoal.isPresent()) {
            budgetGoalRepository.deleteById(param.getBudgetGoalId());
            return new BudgetGoalDeleteResult(true);
        } else {
            throw new RecordNotFoundException("Invalid BudgetGoalId");
        }
    }

    @Override
    public List<BudgetGoalOfUserResult> findUserBudgetGoals(BudgetGoalOfUserParam param) {
        validatorService.validateUserExistence(param.getUserId());
        CategoryEntity category = null;
        if (param.getCategory() != null && !param.getCategory().equals("")) {
            category = validatorService.validateCategoryExistence(param.getCategory());
        }
        Date date = null;
        if (param.getDate() != null && !param.getDate().equals("")) {
            date = DateUtil.toDate(param.getDate());
        }
        List<BudgetGoalEntity> budgetGoalList = null;
        if (category == null && date == null) {
            budgetGoalList = budgetGoalRepository.findUserBudgetGoal(param.getUserId());
        } else if (category != null && date == null) {
            budgetGoalList = budgetGoalRepository.findUserBudgetGoalForSpecificCategory(param.getUserId(), category.getId());
        } else if (category == null && date != null) {
            budgetGoalList = budgetGoalRepository.findUserBudgetGoalInMonth(param.getUserId(), date);
        } else {
            budgetGoalList = budgetGoalRepository.findUserBudgetGoalForSpecificCategoryInMonth(param.getUserId(), category.getId(), date);
        }
        List<BudgetGoalOfUserResult> result = budgetGoalList.stream().map(bg -> new BudgetGoalOfUserResult(bg.getCategory().getName(),
                bg.getMaxAmount(), DateUtil.fromDate(bg.getBudgetDate()))).collect(Collectors.toList());
        return result;
    }
}
