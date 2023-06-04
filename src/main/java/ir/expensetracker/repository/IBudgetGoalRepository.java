package ir.expensetracker.repository;

import ir.expensetracker.entity.BudgetGoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IBudgetGoalRepository extends JpaRepository<BudgetGoalEntity, Integer> {

    @Query(value = "SELECT bg FROM BudgetGoalEntity as bg WHERE bg.user.id=:userId and bg.category.id=:categoryId " +
            "and bg.budgetDate <= :date and bg.budgetDate >= :date ")
    public List<BudgetGoalEntity> findUserBudgetGoalForSpecificCategoryInMonth(Integer userId, Integer categoryId, Date date);

    @Query(value = "SELECT bg FROM BudgetGoalEntity as bg WHERE bg.user.id=:userId and bg.category.id=:categoryId ")
    public List<BudgetGoalEntity> findUserBudgetGoalForSpecificCategory(Integer userId, Integer categoryId);

    @Query(value = "SELECT bg FROM BudgetGoalEntity as bg WHERE bg.user.id=:userId and bg.budgetDate <= :date and bg.budgetDate >= :date ")
    public List<BudgetGoalEntity> findUserBudgetGoalInMonth(Integer userId, Date date);

    @Query(value = "SELECT bg FROM BudgetGoalEntity as bg WHERE bg.user.id=:userId")
    public List<BudgetGoalEntity> findUserBudgetGoal(Integer userId);
}
