package com.digitalbooking.Back.services.serviceInterface;

import com.digitalbooking.Back.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService{

    Category saveCategory(Category category);
    Optional<Category> searchCategory(Integer id);
    List<Category> listCategories();
    Category updateCategory(Category category);
    void deleteCategory(Integer id);
}
