package ir.expensetracker.service;

import ir.expensetracker.api.*;
import ir.expensetracker.entity.CategoryEntity;
import ir.expensetracker.entity.TransactionsEntity;
import ir.expensetracker.entity.UserEntity;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.exception.RecordNotFoundException;
import ir.expensetracker.repository.ITransactionRepository;
import ir.expensetracker.service.facade.ITransactionService;
import ir.expensetracker.service.facade.IValidatorService;
import ir.expensetracker.util.DateUtil;
import ir.expensetracker.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {
    private ITransactionRepository transactionRepository;
    private IValidatorService validatorService;

    @Autowired
    public TransactionService(ITransactionRepository transactionRepository, IValidatorService validatorService){
        this.transactionRepository=transactionRepository;
        this.validatorService=validatorService;
    }

    @Override
    public TransactionCreateResult createTransaction(TransactionCreateParam param) {
        UserEntity user=validatorService.validateUserExistence(param.getUserId());
        CategoryEntity category=validatorService.validateCategoryExistence(param.getCategory());

        if(param.getAmount()==null || param.getAmount()==0){
            throw new InvalidParameterException("Invalid Amount");
        }

        Date date=DateUtil.getDate(param.getDate());
        TransactionsEntity transaction=new TransactionsEntity();
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setAmount(param.getAmount());
        transaction.setDescription(param.getDescription());
        transaction.setTransactionDate(date);
        transaction=transactionRepository.save(transaction);
        String alert="";
        //TODO implement alert if it reaches budget goal
        return new TransactionCreateResult(transaction.getId(),alert);
    }

    @Override
    public TransactionDeleteResult deleteTransaction(TransactionDeleteParam param) {
        Optional<TransactionsEntity> transaction=transactionRepository.findById(param.getTransactionId());
        if(transaction.isPresent()){
            transactionRepository.deleteById(param.getTransactionId());
            return new TransactionDeleteResult(true);
        }else{
          throw new RecordNotFoundException("Invalid TransactionId");
        }
    }

    @Override
    public List<AllTransactionsResult> findAllTransactionsOfUser(AllTransactionsParam param) {
        validatorService.validateUserExistence(param.getUserId());
        List<TransactionsEntity> transactionsEntityList=transactionRepository.findAllTransactionsOfUser(param.getUserId());
        List<AllTransactionsResult> result=transactionsEntityList.stream().map(t->new AllTransactionsResult(t.getCategory().getName(),t.getDescription(),DateUtil.fromDate(t.getTransactionDate()),t.getAmount())).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<AllTransactionsResult> findTransactionsOfUserInMonth(AllTransactionsInMonthParam param) {
        validatorService.validateUserExistence(param.getUserId());
        Date date=DateUtil.getDate(param.getDate());
        List<TransactionsEntity> transactionsEntityList=transactionRepository.findAllTransactionsOfUserInMonth(param.getUserId(),date);
        List<AllTransactionsResult> result=transactionsEntityList.stream().map(t->new AllTransactionsResult(t.getCategory().getName(),t.getDescription(),DateUtil.fromDate(t.getTransactionDate()),t.getAmount())).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<AllTransactionsResult> findTransactionsOfUserInMonthByCategory(AllTransactionsInMonthByCategoryParam param) {
        validatorService.validateUserExistence(param.getUserId());
        CategoryEntity category=validatorService.validateCategoryExistence(param.getCategory());
        Date date=DateUtil.getDate(param.getDate());
        List<TransactionsEntity> transactionsEntityList=transactionRepository.findAllTransactionsOfUserForSpecificCategoryInMonth(param.getUserId(),category.getId(),date);
        List<AllTransactionsResult> result=transactionsEntityList.stream().map(t->new AllTransactionsResult(t.getCategory().getName(),t.getDescription(),DateUtil.fromDate(t.getTransactionDate()),t.getAmount())).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<AllTransactionsResult> findTransactionsOfUserInMonthGreaterThanSpecificAmount(AllTransactionsInMonthGreaterThanSpecificAmountParam param) {
        validatorService.validateUserExistence(param.getUserId());
        Date date=DateUtil.getDate(param.getDate());
        List<TransactionsEntity> transactionsEntityList=transactionRepository.findAllTransactionsOfUserGreaterThanSpecificAmountInMonth(param.getUserId(),param.getAmount(),date);
        List<AllTransactionsResult> result=transactionsEntityList.stream().map(t->new AllTransactionsResult(t.getCategory().getName(),t.getDescription(),DateUtil.fromDate(t.getTransactionDate()),t.getAmount())).collect(Collectors.toList());
        return result;
    }

    @Override
    public SaveTransactionsInExcelResult saveTransactionsOfUserInMonthAtExcel(AllTransactionsInMonthParam param) {
        UserEntity user=validatorService.validateUserExistence(param.getUserId());
        Date date=DateUtil.getDate(param.getDate());
        List<TransactionsEntity> transactionsEntityList=transactionRepository.findAllTransactionsOfUserInMonth(param.getUserId(),date);
        List<AllTransactionsResult> result=transactionsEntityList.stream().map(t->new AllTransactionsResult(t.getCategory().getName(),t.getDescription(),DateUtil.fromDate(t.getTransactionDate()),t.getAmount())).collect(Collectors.toList());
        ExcelUtil.saveInExcelFile(("transactions-"+user.getUsername()+".xlsx"),result);
        return null;
    }
}
