package spring.study.dao;
/*Создайте приложение для хранения дней рождений Ваших друзей.
Приложение должно хранить имя (более одного символа) и фамилию (более одного символа) друга,
 а также его дату рождения (любая дата из прошлого, включая сегодняшнюю).
 Создайте метод для добавления записи о дне рождения, метод получения всех хранимых записей,
 метод получения сегодняшних именинников и методы поиска (именинники по дате,
 дата по имени и фамилии). Если при поиске данные не найдены, то должно быть брошено
 исключение, а контроллер должен обработать исключение и дать ответ 404.
Выполните валидацию данных при создании записи. В ответ на невалидные данные должен
быть дан ответ с кодом 400.*/

import org.springframework.stereotype.Component;
import spring.study.model.Friend;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class FriendBirthdayDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/friends_birthday_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORT = "root";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Friend> getAll() {
        List<Friend> friends = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select * from friend"
            );
            while (resultSet.next()) {
                Friend friend = new Friend();
                friend.setId(resultSet.getLong("id"));
                friend.setFirstName(resultSet.getString("first_name"));
                friend.setLastName(resultSet.getString("last_name"));
                friend.setBirthday(resultSet.getDate("birthday").toLocalDate());
                friends.add(friend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public Friend getFriendById(long id) {
        Friend friend = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from friend where id = ?"
            );
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                friend = new Friend();
                friend.setId(resultSet.getLong("id"));
                friend.setFirstName(resultSet.getString("first_name"));
                friend.setLastName(resultSet.getString("last_name"));
                friend.setBirthday(resultSet.getDate("birthday").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (friend == null) {
            throw new NoSuchElementException("No such friend");
        }
        return friend;
    }

    public void save(Friend friend) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into friend(first_name, last_name, birthday) values (?, ?, ?)"
            );
            preparedStatement.setString(1, friend.getFirstName());
            preparedStatement.setString(2, friend.getLastName());
            preparedStatement.setDate(3, Date.valueOf(friend.getBirthday()));
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Friend> getByDate(LocalDate birthday) {
        List<Friend> friends = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from friend where birthday = ?"
            );
            preparedStatement.setDate(1, Date.valueOf(birthday));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Friend friend = new Friend();
                friend.setBirthday(resultSet.getDate("birthday").toLocalDate());
                friend.setId(resultSet.getLong("id"));
                friend.setFirstName(resultSet.getString("first_name"));
                friend.setLastName(resultSet.getString("last_name"));
                friends.add(friend);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (friends.isEmpty()) {
            throw new NoSuchElementException("Friends not found");
        }
        return friends;
    }

    public Friend getByFullname(String firstName, String lastName) {
        Friend friend = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from friend where first_name=? and last_name=?"
            );
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                friend = new Friend();
                friend.setId(resultSet.getLong("id"));
                friend.setFirstName(resultSet.getString("first_name"));
                friend.setLastName(resultSet.getString("last_name"));
                friend.setBirthday(resultSet.getDate("birthday").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (friend == null) {
            throw new NoSuchElementException("No such friend!");
        }
        return friend;
    }

    public void delete(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from friend where id = ?"
            );
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
