package com.hongdatchy.dao;

import com.hongdatchy.model.Admin;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    List<Admin> findAll() throws SQLException;
}
