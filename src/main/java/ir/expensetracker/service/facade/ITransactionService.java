package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;

import java.util.List;

public interface ITransactionService {

    public TransactionCreateResult createTransaction(TransactionCreateParam param,String jwt);
    public TransactionDeleteResult deleteTransaction(TransactionDeleteParam param);
    public List<AllTransactionsResult> findAllTransactionsOfUser(String jwt);
    public List<AllTransactionsWithSumAmountInCategoryResult> findTransactionsOfUserInMonth(AllTransactionsInMonthParam param,String jwt);
    public List<AllTransactionsResult> findTransactionsOfUserInMonthDetails(AllTransactionsInMonthParam param,String jwt);
    public List<AllTransactionsResult> findTransactionsOfUserInMonthByCategory(AllTransactionsInMonthByCategoryParam param,String jwt);
    public List<AllTransactionsResult> findTransactionsOfUserInMonthGreaterThanSpecificAmount(AllTransactionsInMonthGreaterThanSpecificAmountParam param,String jwt);
    public SaveTransactionsInExcelResult saveTransactionsOfUserInMonthAtExcel(AllTransactionsInMonthParam param,String jwt);
}
