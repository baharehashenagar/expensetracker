package ir.expensetracker.repository;

import ir.expensetracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT u FROM UserEntity as u WHERE u.username=:userName")
    public UserEntity findByUsername(String userName);
}
