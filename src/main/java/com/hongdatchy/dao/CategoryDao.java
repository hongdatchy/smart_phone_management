package com.hongdatchy.dao;

import com.hongdatchy.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> findAll() throws SQLException;

    Category insertCategory(Category category) throws SQLException;

    boolean updateCategory(Category category) throws SQLException;

    boolean deleteCategory(int id) throws SQLException;
}
