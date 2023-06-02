package ir.expensetracker.repository;

import ir.expensetracker.entity.BudgetGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IBudgetGoalRepository extends JpaRepository<BudgetGoalEntity, Integer> {

    @Query(value = "SELECT bg FROM BudgetGoalEntity as bg WHERE bg.userId=:userId and bg.categoryId=:categoryId " +
            "and bg.budgetDate <= :date and bg.budgetDate >= :date ")
    public List<BudgetGoalEntity> findUserBudgetGoalForSpecificMonth(Integer userId, Integer categoryId, Date date);
}
