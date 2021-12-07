package com.hongdatchy.service;

import com.hongdatchy.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> findAll() throws SQLException;

    Product insertProduct(Product product) throws SQLException;

    boolean updateProduct(Product product) throws SQLException;

    boolean deleteProduct(int id) throws SQLException;

    List<Product> sortByField(String field) throws SQLException;
}
