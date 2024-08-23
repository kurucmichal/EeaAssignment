package com.kuruc;

import com.kuruc.domain.User;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;

public class UserDAO{
    private final Connection connection;

    public UserDAO() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "user", "");
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE SUSERS (USER_ID INT, USER_GUID VARCHAR(255), USER_NAME VARCHAR(255))");
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO SUSERS (USER_ID, USER_GUID, USER_NAME) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, (int) user.getId());
        stmt.setString(2, user.getGuId());
        stmt.setString(3, user.getName());
        stmt.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM SUSERS";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("USER_ID"));
            user.setGuId(rs.getString("USER_GUID"));
            user.setName(rs.getString("USER_NAME"));
            users.add(user);
        }
        return users;
    }

    public void deleteAllUsers() throws SQLException {
        String sql = "DELETE FROM SUSERS";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
    }
}