package com.hongdatchy.service;

import com.hongdatchy.model.Admin;
import com.hongdatchy.model.LoginForm;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    List<Admin> findAll() throws SQLException;

    boolean login(LoginForm loginForm) throws SQLException;
}
