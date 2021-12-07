package com.hongdatchy.service;

import com.hongdatchy.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {

    List<Category>findAll() throws SQLException;

    Category insertCategory(String name) throws SQLException;

    boolean updateCategory(Category category) throws SQLException;

    boolean deleteCategory(int id) throws SQLException;
}
