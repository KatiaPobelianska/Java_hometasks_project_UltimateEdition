package spring.study.dao;

import org.springframework.stereotype.Component;
import spring.study.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/shop_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Product> getAll(){
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product");
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    public Product getProductById(int id){
        Product product = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
    public double getAverageRatingForProduct(int productId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT AVG(mark) AS average_mark FROM review WHERE product_id = ?");
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double averageRating = resultSet.getDouble("average_mark");
                return Math.round(averageRating * 100) / 100.0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
