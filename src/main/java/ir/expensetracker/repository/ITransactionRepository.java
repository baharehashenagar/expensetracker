package ir.expensetracker.repository;

import ir.expensetracker.entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionsEntity, Integer> {

    @Query(value = "SELECT t FROM TransactionsEntity as t WHERE t.user.id =:userId")
    public List<TransactionsEntity> findAllTransactionsOfUser(Integer userId);

    @Query(value = "SELECT t FROM TransactionsEntity as t WHERE t.user.id =:userId and t.category.id=:categoryId and t.transactionDate <= :date and t.transactionDate >= :date ")
    public List<TransactionsEntity> findAllTransactionsOfUserForSpecificCategoryInMonth(Integer userId, Integer categoryId, Date date);

    @Query(value = "SELECT t FROM TransactionsEntity as t WHERE t.user.id =:userId and t.amount >=:amount and t.transactionDate <= :date and t.transactionDate >= :date ")
    public List<TransactionsEntity> findAllTransactionsOfUserGreaterThanSpecificAmountInMonth(Integer userId, Integer amount, Date date);

    @Query(value = "SELECT t FROM TransactionsEntity as t WHERE t.user.id=:userId and t.transactionDate <= :date and t.transactionDate >= :date ")
    public List<TransactionsEntity> findAllTransactionsOfUserInMonth(Integer userId, Date date);

}
