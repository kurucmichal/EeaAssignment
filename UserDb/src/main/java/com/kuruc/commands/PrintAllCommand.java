package com.kuruc.commands;

import com.kuruc.UserDAO;
import com.kuruc.domain.User;

import java.sql.SQLException;
import java.util.List;

public class PrintAllCommand implements DbCommand {
    private final UserDAO userDao;

    public PrintAllCommand(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public void execute() throws SQLException {
        List<User> users = userDao.getAllUsers();
        users.forEach(System.out::println);
    }
}