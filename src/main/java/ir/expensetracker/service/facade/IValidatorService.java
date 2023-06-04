package ir.expensetracker.service.facade;

import ir.expensetracker.entity.CategoryEntity;
import ir.expensetracker.entity.UserEntity;

public interface IValidatorService {
    public UserEntity validateUserExistence(Integer userId);
    public CategoryEntity validateCategoryExistence(String categoryName);
}
