package ir.expensetracker.repository;

import ir.expensetracker.entity.TransactionsEntity;
import ir.expensetracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionsEntity, Integer> {

    @Query(value = "SELECT t FROM TransactionsEntity as t WHERE t.userId =:userId")
    public UserEntity findAllTransactionsOfUser(Integer userId);

    @Query(value = "SELECT t FROM TransactionsEntity as t WHERE t.userId =:userId and t.categoryId=:categoryId")
    public UserEntity findAllTransactionsOfUserForSpecificCategory(Integer userId, Integer categoryId);

    @Query(value = "SELECT t FROM TransactionsEntity as t WHERE t.userId =:userId and t.amount >=:amount")
    public UserEntity findAllTransactionsOfUserGreaterThanSpecificAmount(Integer userId, Integer amount);

}
