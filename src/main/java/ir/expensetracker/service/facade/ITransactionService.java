package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;
import java.util.List;

public interface ITransactionService {

    public TransactionCreateResult createTransaction(TransactionCreateParam param);
    public TransactionDeleteResult deleteTransaction(TransactionDeleteParam param);
    public List<AllTransactionsResult> findAllTransactionsOfUser(AllTransactionsParam param);
    public List<AllTransactionsResult> findTransactionsOfUserInMonth(AllTransactionsInMonthParam param);
    public List<AllTransactionsResult> findTransactionsOfUserInMonthByCategory(AllTransactionsInMonthByCategoryParam param);
    public List<AllTransactionsResult> findTransactionsOfUserInMonthGreaterThanSpecificAmount(AllTransactionsInMonthGreaterThanSpecificAmountParam param);
    public SaveTransactionsInExcelResult saveTransactionsOfUserInMonthAtExcel(AllTransactionsInMonthParam param);
}
