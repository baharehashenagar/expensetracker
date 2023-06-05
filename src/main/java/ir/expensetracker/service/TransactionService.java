package ir.expensetracker.service;

import ir.expensetracker.api.*;
import ir.expensetracker.authentication.JWTUtil;
import ir.expensetracker.entity.CategoryEntity;
import ir.expensetracker.entity.TransactionsEntity;
import ir.expensetracker.entity.UserEntity;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.exception.RecordNotFoundException;
import ir.expensetracker.repository.ITransactionRepository;
import ir.expensetracker.service.facade.IBudgetGoalService;
import ir.expensetracker.service.facade.ITransactionService;
import ir.expensetracker.service.facade.IValidatorService;
import ir.expensetracker.util.DateUtil;
import ir.expensetracker.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {
    private ITransactionRepository transactionRepository;
    private IValidatorService validatorService;
    private IBudgetGoalService budgetGoalService;

    @Autowired
    public TransactionService(ITransactionRepository transactionRepository, IValidatorService validatorService,
                              IBudgetGoalService budgetGoalService) {
        this.transactionRepository = transactionRepository;
        this.validatorService = validatorService;
        this.budgetGoalService = budgetGoalService;
    }

    @Override
    @Transactional
    public TransactionCreateResult createTransaction(TransactionCreateParam param, String jwt) {
        UserEntity user = validatorService.validateUserExistence(JWTUtil.getUserIdFromToken(jwt));
        CategoryEntity category = validatorService.validateCategoryExistence(param.getCategory());

        if (param.getAmount() == null || param.getAmount() == 0) {
            throw new InvalidParameterException("Invalid Amount");
        }

        Date date = DateUtil.getDate(param.getDate());
        TransactionsEntity transaction = new TransactionsEntity();
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setAmount(param.getAmount());
        transaction.setDescription(param.getDescription());
        transaction.setTransactionDate(date);
        transaction = transactionRepository.save(transaction);
        String alert = getAlert(date, category.getName(), jwt);
        return new TransactionCreateResult(transaction.getId(), alert);
    }

    @Override
    @Transactional
    public TransactionDeleteResult deleteTransaction(TransactionDeleteParam param) {
        Optional<TransactionsEntity> transaction = transactionRepository.findById(param.getTransactionId());
        if (transaction.isPresent()) {
            transactionRepository.deleteById(param.getTransactionId());
            return new TransactionDeleteResult(true);
        } else {
            throw new RecordNotFoundException("Invalid TransactionId");
        }
    }

    @Override
    @Transactional
    public List<AllTransactionsResult> findAllTransactionsOfUser(String jwt) {
        Integer userId = JWTUtil.getUserIdFromToken(jwt);
        validatorService.validateUserExistence(userId);
        List<TransactionsEntity> transactionsEntityList = transactionRepository.findAllTransactionsOfUser(userId);
        List<AllTransactionsResult> result = transactionsEntityList.stream().map(t -> new AllTransactionsResult(t.getId(),t.getCategory().getName(), t.getDescription(), DateUtil.fromDate(t.getTransactionDate()), t.getAmount())).collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public List<AllTransactionsWithSumAmountInCategoryResult> findTransactionsOfUserInMonth(AllTransactionsInMonthParam param, String jwt) {
        Integer userId = JWTUtil.getUserIdFromToken(jwt);
        validatorService.validateUserExistence(userId);
        Date fromDate = DateUtil.getDate(param.getFromDate());
        Date toDate = DateUtil.getDate(param.getToDate());
        List<TransactionsEntity> transactionsEntityList = transactionRepository.findAllTransactionsOfUserInMonth(userId, fromDate, toDate);

        Map<String, Map<String, Integer>> sumAmountByCategoryResult = transactionsEntityList.stream()
                .map(t -> new AllTransactionsResult(t.getId(),t.getCategory().getName(), t.getDescription(), DateUtil.fromDate(t.getTransactionDate()), t.getAmount()))
                .collect(Collectors.groupingBy(AllTransactionsResult::getCategory,
                        Collectors.groupingBy(AllTransactionsResult::getDate,
                                Collectors.summingInt(AllTransactionsResult::getAmount))));

        List<AllTransactionsWithSumAmountInCategoryResult> result = new ArrayList<>();
        sumAmountByCategoryResult.forEach((category, categoryMapValue) ->
                categoryMapValue.forEach((trxDate, sumAmount) ->
                        result.add(new AllTransactionsWithSumAmountInCategoryResult(category, trxDate, sumAmount))));
        return result;
    }

    @Override
    @Transactional
    public List<AllTransactionsResult> findTransactionsOfUserInMonthDetails(AllTransactionsInMonthParam param, String jwt) {
        Integer userId = JWTUtil.getUserIdFromToken(jwt);
        validatorService.validateUserExistence(userId);
        Date fromDate = DateUtil.getDate(param.getFromDate());
        Date toDate = DateUtil.getDate(param.getToDate());
        List<TransactionsEntity> transactionsEntityList = transactionRepository.findAllTransactionsOfUserInMonth(userId, fromDate, toDate);
        List<AllTransactionsResult> result = transactionsEntityList.stream().map(t -> new AllTransactionsResult(t.getId(),t.getCategory().getName(), t.getDescription(), DateUtil.fromDate(t.getTransactionDate()), t.getAmount())).collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public List<AllTransactionsResult> findTransactionsOfUserInMonthByCategory(AllTransactionsInMonthByCategoryParam param, String jwt) {
        Integer userId = JWTUtil.getUserIdFromToken(jwt);
        validatorService.validateUserExistence(userId);
        CategoryEntity category = validatorService.validateCategoryExistence(param.getCategory());
        Date fromDate = DateUtil.getDate(param.getFromDate());
        Date toDate = DateUtil.getDate(param.getToDate());
        List<TransactionsEntity> transactionsEntityList = transactionRepository.findAllTransactionsOfUserForSpecificCategoryInMonth(userId, category.getId(), fromDate, toDate);
        List<AllTransactionsResult> result = transactionsEntityList.stream().map(t -> new AllTransactionsResult(t.getId(),t.getCategory().getName(), t.getDescription(), DateUtil.fromDate(t.getTransactionDate()), t.getAmount())).collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public List<AllTransactionsResult> findTransactionsOfUserInMonthGreaterThanSpecificAmount(AllTransactionsInMonthGreaterThanSpecificAmountParam param, String jwt) {
        Integer userId = JWTUtil.getUserIdFromToken(jwt);
        validatorService.validateUserExistence(userId);
        Date fromDate = DateUtil.getDate(param.getFromDate());
        Date toDate = DateUtil.getDate(param.getToDate());
        List<TransactionsEntity> transactionsEntityList = transactionRepository.findAllTransactionsOfUserGreaterThanSpecificAmountInMonth(userId, param.getAmount(), fromDate, toDate);
        List<AllTransactionsResult> result = transactionsEntityList.stream().map(t -> new AllTransactionsResult(t.getId(),t.getCategory().getName(), t.getDescription(), DateUtil.fromDate(t.getTransactionDate()), t.getAmount())).collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public SaveTransactionsInExcelResult saveTransactionsOfUserInMonthAtExcel(AllTransactionsInMonthParam param, String jwt) {
        Integer userId = JWTUtil.getUserIdFromToken(jwt);
        UserEntity user = validatorService.validateUserExistence(userId);
        Date fromDate = DateUtil.getDate(param.getFromDate());
        Date toDate = DateUtil.getDate(param.getToDate());
        List<TransactionsEntity> transactionsEntityList = transactionRepository.findAllTransactionsOfUserInMonth(userId, fromDate, toDate);
        List<AllTransactionsResult> result = transactionsEntityList.stream().map(t -> new AllTransactionsResult(t.getId(),t.getCategory().getName(), t.getDescription(), DateUtil.fromDate(t.getTransactionDate()), t.getAmount())).collect(Collectors.toList());
        ExcelUtil.saveInExcelFile(("transactions-" + user.getUsername() + ".xlsx"), result);
        return new SaveTransactionsInExcelResult(true);
    }

    @Transactional
    String getAlert(Date trxDate, String category, String jwt) {
        StringBuilder alert = new StringBuilder();

        String date = DateUtil.fromDate(trxDate);
        List<BudgetGoalOfUserResult> budgetGoal = budgetGoalService.findUserBudgetGoals(new BudgetGoalOfUserParam(date, date, category), jwt);
        if (budgetGoal != null && budgetGoal.size() > 0) {
            AllTransactionsInMonthParam input = new AllTransactionsInMonthParam();
            input.setFromDate(budgetGoal.get(0).getFromDate());
            input.setToDate(budgetGoal.get(0).getToDate());
            List<AllTransactionsWithSumAmountInCategoryResult> result = findTransactionsOfUserInMonth(input, jwt);
            for (int i = 0; i < result.size(); i++) {
                AllTransactionsWithSumAmountInCategoryResult transaction = result.get(i);
                if (transaction.getCategory().equals(category) && budgetGoal.get(0).getMaxAmount() < transaction.getTotalAmount()) {
                    alert.append("You spent too much money for ")
                            .append(transaction.getCategory())
                            .append(". your budget goal was ")
                            .append(budgetGoal.get(0).getMaxAmount())
                            .append(", but you paid ")
                            .append(transaction.getTotalAmount())
                            .append(" until now.");
                }
            }
        }

        return alert.toString();
    }
}
