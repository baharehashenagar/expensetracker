package ir.expensetracker.service.facade;

public interface ITransactionService {

    void createTransaction();
    void deleteTransaction();
    void findAllTransactionsOfUser();
    void findTransactionsOfUserInMonth();
    void findTransactionsOfUserInMonthByCategory();
    void findTransactionsOfUserInMonthGreaterThanSpecificAmount();
    void saveTransactionsOfUserInMonthAtExcel();
}
