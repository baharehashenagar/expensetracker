package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;
import ir.expensetracker.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    public CategoryCreateResult createCategory(CategoryCreateParam param, String jwt);

    public CategoryEntity getCategory(String categoryName, String jwt);

    public List<CategoryEntity> findAllCategories(String jwt);
}
