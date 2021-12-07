package com.hongdatchy.model;

import com.hongdatchy.common.AppConfig;

import java.sql.*;

public class MyConnection {

    public static Connection connection = null;

    public void driverTest() throws ClassNotFoundException {
        try {
            Class.forName(AppConfig.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("JDBC Driver not found" + e.getMessage());
        }
    }

    public Connection connectDataBase() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            driverTest();
            try {
                connection = DriverManager.getConnection(AppConfig.URL_DATABASE, AppConfig.USERNAME, AppConfig.PASSWORD);
                if (connection != null) System.out.println("Connect DB successfully");
            } catch (Exception e) {
                throw new SQLException("Connect DB fail " + e.getMessage());
            }
        }
        return connection;
    }

    public PreparedStatement prepare(String sql) {
        try {
            System.out.println(">> "+sql);
            return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //ResultSet.TYPE_SCROLL_SENSITIVE: cho phép con trỏ resultSet chạy từ bản ghi đầu đến cuối.
            //ResultSet.CONCUR_UPDATABLE: tạo ra một đuối tượng resultSet có thể được cập nhập.
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public PreparedStatement prepareUpdate(String sql) {
        try {
            System.out.println(">> "+sql);
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Statement.RETURN_GENERATED_KEYS trả về id của bản ghi vừa insert thành công
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void closeConnection() throws SQLException {
        if(connection != null) {
            connection.close();
            System.out.println("Connection is closed");
        }
    }
}
