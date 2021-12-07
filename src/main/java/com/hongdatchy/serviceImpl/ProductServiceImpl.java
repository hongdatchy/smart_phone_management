package com.hongdatchy.serviceImpl;

import com.hongdatchy.dao.ProductDao;
import com.hongdatchy.daoImpl.ProductDaoImpl;
import com.hongdatchy.model.Product;
import com.hongdatchy.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> findAll() throws SQLException {
        return productDao.findAll();
    }

    @Override
    public Product insertProduct(Product product) throws SQLException {
        return productDao.insertProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        return productDao.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        return productDao.deleteProduct(id);
    }

    @Override
    public List<Product> sortByField(String field) throws SQLException {
        return productDao.sortByField(field);
    }
}
