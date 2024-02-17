package spring.study.dao;


import org.springframework.stereotype.Component;
import spring.study.model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class ReviewDao {
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

    public List<Review> getListOfAllReviews(){
        List<Review> reviews = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from review");
            while (resultSet.next()){
                Review review = new Review();
                review.setId(resultSet.getInt("id"));
                review.setName(resultSet.getString("name"));
                review.setDescription(resultSet.getString("description"));
                review.setMark(resultSet.getInt("mark"));
                review.setProductId(resultSet.getInt("product_id"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }
    public List<Review> getListOfReviewsById(int productId){
        List<Review> reviews = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from review where product_id = ?");
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Review review = new Review();
                review.setProductId(resultSet.getInt("product_id"));
                review.setName(resultSet.getString("name"));
                review.setMark(resultSet.getInt("mark"));
                review.setDescription(resultSet.getString("description"));
                review.setId(resultSet.getInt("id"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }
    public void save(Review review){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into review(name, description, mark, product_id) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, review.getName());
            preparedStatement.setString(2, review.getDescription());
            preparedStatement.setInt(3, review.getMark());
            preparedStatement.setInt(4, review.getProductId());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
