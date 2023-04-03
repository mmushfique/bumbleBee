package com.mush.bumblebee.service;

import com.mush.bumblebee.dao.CategoryManager;
import com.mush.bumblebee.domain.Category;
import com.mush.bumblebee.injector.Injector;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService{
    private static CategoryServiceImpl service;
    private final Injector injector = new Injector();
    private final CategoryManager categoryManager=injector.getCategoryManager();

    private CategoryServiceImpl() {
    }

    public static synchronized CategoryServiceImpl getCategoryServiceInstance() {
        if (service == null) {
            service = new CategoryServiceImpl();
        }
        return service;
    }
    @Override
    public boolean registerCategory(Category category) throws SQLException, ClassNotFoundException, IOException {
        return categoryManager.registerCategory(category);
    }

    @Override
    public Category getSpecificCategory(String categoryName) throws SQLException, ClassNotFoundException, IOException {
        return categoryManager.getSpecificCategory(categoryName);
    }

    @Override
    public List<Category> getAllCategorys() throws SQLException, ClassNotFoundException, IOException {
        return categoryManager.getAllCategorys();
    }

    @Override
    public boolean updateCategory(Category category) throws SQLException, IOException, ClassNotFoundException {
        return categoryManager.updateCategory(category);
    }

    @Override
    public boolean deleteCategory(String categoryName) throws SQLException, ClassNotFoundException, IOException {
        return categoryManager.deleteCategory(categoryName);
    }
}
