package ir.expensetracker.repository;

import ir.expensetracker.entity.CategoryEntity;
import ir.expensetracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query(value = "SELECT c FROM CategoryEntity as c WHERE c.name=:category")
    public CategoryEntity findByCategoryName(String category);
}
