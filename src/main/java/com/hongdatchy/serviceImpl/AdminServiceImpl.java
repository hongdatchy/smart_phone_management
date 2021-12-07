package com.hongdatchy.serviceImpl;

import com.hongdatchy.dao.AdminDao;
import com.hongdatchy.daoImpl.AdminDaoImpl;
import com.hongdatchy.model.Admin;
import com.hongdatchy.model.LoginForm;
import com.hongdatchy.service.AdminService;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public List<Admin> findAll() throws SQLException {
        return adminDao.findAll();
    }

    @Override
    public boolean login(LoginForm loginForm) throws SQLException {
        return adminDao.findAll().stream()
                .filter(admin -> admin.getUsername().equals(loginForm.getUsername())
                        && admin.getPassword().equals(loginForm.getPassword()))
                .count() == 1;
    }
}
