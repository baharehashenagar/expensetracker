package ir.expensetracker.service;

import ir.expensetracker.api.*;
import ir.expensetracker.authentication.JWTUtil;
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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public BudgetGoalCreateResult createBudgetGoal(BudgetGoalCreateParam param, String jwt) {
        Integer userId = JWTUtil.getUserIdFromToken(jwt);
        UserEntity user = validatorService.validateUserExistence(userId);
        CategoryEntity category = validatorService.validateCategoryExistence(param.getCategory());
        Date fromDate = DateUtil.getDate(param.getFromDate());
        Date toDate = DateUtil.getDate(param.getToDate());
        List<BudgetGoalEntity> result = budgetGoalRepository.findUserBudgetGoalForSpecificCategoryInMonth(userId, category.getId(), fromDate, toDate);
        if (result != null && result.size() > 0) {
            throw new InvalidParameterException("Duplicate BudgetGoal");
        }
        BudgetGoalEntity budgetGoal = new BudgetGoalEntity();
        budgetGoal.setUser(user);
        budgetGoal.setCategory(category);
        budgetGoal.setFromDate(fromDate);
        budgetGoal.setToDate(toDate);
        budgetGoal.setMaxAmount(param.getMaxAmount() != null ? param.getMaxAmount() : 0);
        budgetGoal = budgetGoalRepository.save(budgetGoal);
        return new BudgetGoalCreateResult(budgetGoal.getId());
    }

    @Override
    @Transactional
    public BudgetGoalDeleteResult deleteBudgetGoal(BudgetGoalDeleteParam param, String jwt) {
        Optional<BudgetGoalEntity> budgetGoal = budgetGoalRepository.findById(param.getBudgetGoalId());
        if (budgetGoal.isPresent()) {
            budgetGoalRepository.deleteById(param.getBudgetGoalId());
            return new BudgetGoalDeleteResult(true);
        } else {
            throw new RecordNotFoundException("Invalid BudgetGoalId");
        }
    }

    @Override
    @Transactional
    public List<BudgetGoalOfUserResult> findUserBudgetGoals(BudgetGoalOfUserParam param, String jwt) {
        Integer userId = JWTUtil.getUserIdFromToken(jwt);
        validatorService.validateUserExistence(userId);
        CategoryEntity category = null;
        if (param.getCategory() != null && !param.getCategory().equals("")) {
            category = validatorService.validateCategoryExistence(param.getCategory());
        }
        Date fromDate = null;
        if (param.getFromDate() != null && !param.getFromDate().equals("")) {
            fromDate= DateUtil.toDate(param.getFromDate());
        }

        Date toDate = null;
        if (param.getToDate() != null && !param.getToDate().equals("")) {
            toDate = DateUtil.toDate(param.getToDate());
        }
        List<BudgetGoalEntity> budgetGoalList = null;
        if (category == null && fromDate == null) {
            budgetGoalList = budgetGoalRepository.findUserBudgetGoal(userId);
        } else if (category != null && fromDate == null) {
            budgetGoalList = budgetGoalRepository.findUserBudgetGoalForSpecificCategory(userId, category.getId());
        } else if (category == null && fromDate != null) {
            budgetGoalList = budgetGoalRepository.findUserBudgetGoalInMonth(userId, fromDate, toDate);
        } else {
            budgetGoalList = budgetGoalRepository.findUserBudgetGoalForSpecificCategoryInMonth(userId, category.getId(), fromDate, toDate);
        }
        List<BudgetGoalOfUserResult> result = budgetGoalList.stream().map(bg -> new BudgetGoalOfUserResult(bg.getId(), bg.getCategory().getName(),
                bg.getMaxAmount(), DateUtil.fromDate(bg.getFromDate()),DateUtil.fromDate(bg.getToDate()))).collect(Collectors.toList());
        return result;
    }
}
