package com.hongdatchy.daoImpl;

import com.hongdatchy.dao.ProductDao;
import com.hongdatchy.model.MyConnection;
import com.hongdatchy.model.Product;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private MyConnection myConnection = new MyConnection();

    // functions main
    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products;
        String sql = "select * from product where deleted = false";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        products = getList(resultSet);
        return products;
    }

    @Override
    public Product insertProduct(Product product) throws SQLException {
        Product newProduct = null;
        String sql = "insert into product values (null, ?, ?, ?, false, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setDate(3, new Date(new java.util.Date().getTime()));
        preparedStatement.setString(4, "");
        preparedStatement.setString(5, product.getIntroduction());
        preparedStatement.setString(6, product.getSpecification());
        preparedStatement.setBoolean(7, product.isSoldOut());
        preparedStatement.setInt(8, product.getGuarantee());
        preparedStatement.setInt(9, product.getCategoryId());
        preparedStatement.setInt(10, product.getBought());
        preparedStatement.setInt(11, product.getPromotion());
        int rs = preparedStatement.executeUpdate();
        if(rs > 0 ) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.first()) {
                newProduct = findById((int) resultSet.getLong(1));
            }
        }
        return newProduct;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean result = false;
        String sql = "update product set name = ?, price = ?, introduction = ?, specification = ?, sold_out = ?, guarantee = ?, category_id = ?, bought = ?, promotion = ? where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setString(3, product.getIntroduction());
        preparedStatement.setString(4, product.getSpecification());
        preparedStatement.setBoolean(5, product.isSoldOut());
        preparedStatement.setInt(6, product.getGuarantee());
        preparedStatement.setInt(7, product.getCategoryId());
        preparedStatement.setInt(8, product.getBought());
        preparedStatement.setInt(9, product.getPromotion());
        preparedStatement.setInt(10, product.getId());
        int rs = preparedStatement.executeUpdate();
        if(rs > 0) result = true;
        return result;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean result = false;
        String sql = "update product set deleted = true where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);
        int rs = preparedStatement.executeUpdate();
        if(rs > 0) result = true;
        return result;
    }

    @Override
    public List<Product> sortByField(String field) throws SQLException {
        String sql = "select * from product where " +
                "deleted = false order by " + field + " ASC";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }

    // functions reference
    public Product getObj(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getDate("create_date"),
                resultSet.getBoolean("deleted"),
                resultSet.getString("image"),
                resultSet.getString("introduction"),
                resultSet.getString("specification"),
                resultSet.getBoolean("sold_out"),
                resultSet.getInt("guarantee"),
                resultSet.getInt("category_id"),
                resultSet.getInt("bought"),
                resultSet.getInt("promotion"));
    }

    public List<Product> getList(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        if(resultSet.first()){
            do {
                products.add(getObj(resultSet));
            }while (resultSet.next());
        }
        return products;
    }

    public Product findById(int id) throws SQLException {
        Product product = null;
        String sql = "select * from product where deleted = false and id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, id); // dùng để set giá trị vào index chấm hỏi tương ứng từ 1
        ResultSet resultSet =  preparedStatement.executeQuery();
        if(resultSet.first()) {
            product = getObj(resultSet);
        }
        return product;
    }
}
