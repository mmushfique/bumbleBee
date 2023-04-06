package com.mush.bumblebee.service;

import com.mush.bumblebee.domain.Category;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    String registerCategory(Category category) throws SQLException, ClassNotFoundException, IOException;

    Category getSpecificCategory(String categoryName) throws SQLException, ClassNotFoundException, IOException;

    List<Category> getAllCategorys() throws SQLException, ClassNotFoundException, IOException;

    String updateCategory(Category category) throws SQLException, IOException, ClassNotFoundException;

    boolean deleteCategory(String categoryName) throws SQLException, ClassNotFoundException, IOException;
}
