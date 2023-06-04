package ir.expensetracker.repository;

import ir.expensetracker.entity.RemindersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface IRemindersRepository extends JpaRepository<RemindersEntity, Integer> {

    @Query(value = "SELECT r FROM RemindersEntity as r WHERE r.user.id =:userId and r.dueDate >= :date and r.dueDate <= :date")
    public List<RemindersEntity> findUserRemindersForSpecificDate(Integer userId, Date date);

    @Query(value = "SELECT r FROM RemindersEntity as r WHERE r.user.id =:userId")
    public List<RemindersEntity> findUserReminders(Integer userId);
}
