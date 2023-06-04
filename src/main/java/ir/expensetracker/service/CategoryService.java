package ir.expensetracker.service;

import ir.expensetracker.api.*;
import ir.expensetracker.entity.CategoryEntity;
import ir.expensetracker.exception.InvalidParameterException;
import ir.expensetracker.repository.ICategoryRepository;
import ir.expensetracker.service.facade.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private ICategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    @Override
    public CategoryCreateResult createCategory(CategoryCreateParam param) {
        if(param.getCategory()==null || param.getCategory().equals("")){
            throw new InvalidParameterException("Category must not be null");
        }
        CategoryEntity result=getCategory(param.getCategory());
        if(result!=null){
            throw new InvalidParameterException("Duplicate Category");
        }
        CategoryEntity category=new CategoryEntity();
        category.setName(param.getCategory());
        category=categoryRepository.save(category);
        return new CategoryCreateResult(category.getId());
    }

    @Override
    public CategoryEntity getCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<CategoryEntity> findAllCategories() {
        return categoryRepository.findAll();
    }
}
