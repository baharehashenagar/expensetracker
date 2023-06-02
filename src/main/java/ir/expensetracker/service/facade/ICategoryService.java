package ir.expensetracker.service.facade;

import ir.expensetracker.api.CategoryCreateParam;
import ir.expensetracker.api.CategoryCreateResult;
import ir.expensetracker.api.CategoryDeleteParam;
import ir.expensetracker.api.CategoryDeleteResult;

public interface ICategoryService {
    public CategoryCreateResult createCategory(CategoryCreateParam param);
    public CategoryDeleteResult deleteCategory(CategoryDeleteParam param);
}
