package ir.expensetracker.repository;

import ir.expensetracker.entity.RemindersEntity;
import ir.expensetracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IRemindersRepository extends JpaRepository<RemindersEntity, Integer> {

    @Query(value = "SELECT r FROM RemindersEntity as r WHERE r.userId =: userId and r.dueDate >= :date and r.dueDate <= :date")
    public UserEntity findUserRemindersForSpecificDate(Integer userId, Date date);
}
