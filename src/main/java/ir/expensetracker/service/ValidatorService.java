package ir.expensetracker.service;

import ir.expensetracker.entity.CategoryEntity;
import ir.expensetracker.entity.UserEntity;
import ir.expensetracker.exception.RecordNotFoundException;
import ir.expensetracker.service.facade.ICategoryService;
import ir.expensetracker.service.facade.IUserService;
import ir.expensetracker.service.facade.IValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidatorService implements IValidatorService {

    private IUserService userService;
    private ICategoryService categoryService;

    @Autowired
    public ValidatorService(IUserService userService, ICategoryService categoryService){
        this.userService=userService;
        this.categoryService=categoryService;
    }
    @Override
    public UserEntity validateUserExistence(Integer userId) {
        Optional<UserEntity> user=userService.getUserById(userId);
        if(!user.isPresent()){
            throw new RecordNotFoundException("User Not Found");
        }
        return user.get();
    }

    @Override
    public CategoryEntity validateCategoryExistence(String categoryName) {
        CategoryEntity category=categoryService.getCategory(categoryName);
        if(category==null){
            throw new RecordNotFoundException("Category Not Found");
        }
        return category;
    }
}
