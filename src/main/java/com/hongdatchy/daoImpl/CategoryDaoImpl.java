package com.hongdatchy.daoImpl;

import com.hongdatchy.dao.CategoryDao;
import com.hongdatchy.model.Category;
import com.hongdatchy.model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private MyConnection myConnection = new MyConnection();

    // functions main
    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> categories;
        String sql = "select * from category where deleted = false";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        categories = getList(resultSet);
        return categories;
    }

    @Override
    public Category insertCategory(Category category) throws SQLException {
        Category newCategory = null;
        String sql = "insert into category (name, deleted) values (?,?)";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setBoolean(2, category.isDeleted());
        int rs = preparedStatement.executeUpdate();
        if(rs> 0){ // update success
            ResultSet resultSet =preparedStatement.getGeneratedKeys();
            if(resultSet.first()){
                newCategory = findById((int) resultSet.getLong(1));
            }
        }
        return newCategory;
    }

    @Override
    public boolean updateCategory(Category category) throws SQLException {
        boolean rs = false;
        String sql = "update category set name = ? where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1,category.getName());
        preparedStatement.setInt(2, category.getId());
        int resultSet = preparedStatement.executeUpdate();
        if(resultSet > 0) rs= true;
        return rs;
    }

    @Override
    public boolean deleteCategory(int id) throws SQLException {
        boolean rs = false;
        String sql = "update category set deleted = true where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);
        int resultSet = preparedStatement.executeUpdate();
        if(resultSet > 0) rs = true;
        return rs;
    }

    // functions reference
    public Category getObj(ResultSet resultSet) throws SQLException {
        Category category;
        category = new Category(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getBoolean("deleted"));
        return category;
    }

    public List<Category> getList(ResultSet resultSet) throws SQLException {
        List<Category> categories = new ArrayList<>();
        if(resultSet.first()){
            do {
                categories.add(getObj(resultSet));
            }while (resultSet.next());
        }
        return categories;
    }

    public Category findById(int id) throws SQLException {
        Category category = null;
        String sql = "select * from category where deleted = false and id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.first()){
            category = getObj(resultSet);
        }
        return category;
    }
}
