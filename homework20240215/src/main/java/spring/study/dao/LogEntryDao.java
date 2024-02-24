package spring.study.dao;

import org.springframework.stereotype.Component;
import spring.study.model.LogEntry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class LogEntryDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/school_db";
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

    public List<LogEntry> getAll(){
        List<LogEntry> logEntries = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from log_entry");
            while (resultSet.next()){
                LogEntry logEntry = new LogEntry();
                logEntry.setId(resultSet.getInt("id"));
                logEntry.setClassName(resultSet.getString("class_name"));
                logEntry.setFullName(resultSet.getString("full_name"));
                logEntry.setSubject(resultSet.getString("subject"));
                logEntry.setMark(resultSet.getInt("mark"));
                logEntries.add(logEntry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logEntries;
    }

    public LogEntry getById(long id){
        LogEntry logEntry = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from log_entry where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            logEntry = new LogEntry();
            logEntry.setId(resultSet.getLong("id"));
            logEntry.setClassName(resultSet.getString("class_name"));
            logEntry.setFullName(resultSet.getString("full_name"));
            logEntry.setSubject(resultSet.getString("subject"));
            logEntry.setMark(resultSet.getInt("mark"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logEntry;
    }
    public List<LogEntry> getAllByClass(String className){
        List<LogEntry> logEntries = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from log_entry where class_name = ?"
            );
            preparedStatement.setString(1, className);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                LogEntry logEntry = new LogEntry();
                logEntry.setId(resultSet.getInt("id"));
                logEntry.setClassName(resultSet.getString("class_name"));
                logEntry.setFullName(resultSet.getString("full_name"));
                logEntry.setSubject(resultSet.getString("subject"));
                logEntry.setMark(resultSet.getInt("mark"));
                logEntries.add(logEntry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logEntries;
    }
    public LogEntry getByClassAndFullname(String className, String fullName){
        LogEntry logEntry = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from log_entry where class_name = ? and full_name = ?"
            );
            preparedStatement.setString(1, className);
            preparedStatement.setString(2, fullName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
            logEntry = new LogEntry();
            logEntry.setId(resultSet.getLong("id"));
            logEntry.setClassName(resultSet.getString("class_name"));
            logEntry.setFullName(resultSet.getString("full_name"));
            logEntry.setSubject(resultSet.getString("subject"));
            logEntry.setMark(resultSet.getInt("mark"));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logEntry;
    }
    public void save(LogEntry logEntry){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into log_entry(class_name, full_name, subject, mark) " +
                    "values (?, ?, ?, ?)");
            preparedStatement.setString(1, logEntry.getClassName());
            preparedStatement.setString(2, logEntry.getFullName());
            preparedStatement.setString(3, logEntry.getSubject());
            preparedStatement.setInt(4, logEntry.getMark());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(LogEntry logEntry, long id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update log_entry set class_name = ?, full_name = ?, subject = ?, mark = ? where id = ?");
            preparedStatement.setString(1, logEntry.getClassName());
            preparedStatement.setString(2, logEntry.getFullName());
            preparedStatement.setString(3, logEntry.getSubject());
            preparedStatement.setInt(4, logEntry.getMark());
            preparedStatement.setLong(5, logEntry.getId());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(long id){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from log_entry where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
