package com.hongdatchy.daoImpl;

import com.hongdatchy.dao.AdminDao;
import com.hongdatchy.model.Admin;
import com.hongdatchy.model.Category;
import com.hongdatchy.model.MyConnection;
import com.hongdatchy.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    private MyConnection myConnection = new MyConnection();


    @Override
    public List<Admin> findAll() throws SQLException {
        List<Admin> admins;
        String sql = "select * from admin";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        admins = getList(resultSet);
        return admins;
    }

    public Admin getObj(ResultSet resultSet) throws SQLException {
        return new Admin(resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password")
                );
    }

    public List<Admin> getList(ResultSet resultSet) throws SQLException {
        List<Admin> admins = new ArrayList<>();
        if(resultSet.first()){
            do {
                admins.add(getObj(resultSet));
            }while (resultSet.next());
        }
        return admins;
    }

}
