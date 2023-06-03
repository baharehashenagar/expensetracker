package ir.expensetracker.service.facade;

import ir.expensetracker.api.*;
import ir.expensetracker.entity.CategoryEntity;

public interface ICategoryService {
    public CategoryCreateResult createCategory(CategoryCreateParam param);
    public CategoryEntity getCategory(String categoryName);
}
