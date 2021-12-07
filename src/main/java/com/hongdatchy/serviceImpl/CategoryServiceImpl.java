package com.hongdatchy.serviceImpl;

import com.hongdatchy.dao.CategoryDao;
import com.hongdatchy.daoImpl.CategoryDaoImpl;
import com.hongdatchy.model.Category;
import com.hongdatchy.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() throws SQLException {
        return categoryDao.findAll();
    }

    @Override
    public Category insertCategory(String name) throws SQLException {
        Category category = new Category();
        category.setName(name);
        category.setDeleted(false);
        return categoryDao.insertCategory(category);
    }

    @Override
    public boolean updateCategory(Category category) throws SQLException {
        return categoryDao.updateCategory(category);
    }

    @Override
    public boolean deleteCategory(int id) throws SQLException {
        return categoryDao.deleteCategory(id);
    }


}
