package com.kuruc.commands;

import com.kuruc.UserDAO;
import com.kuruc.domain.User;

import java.sql.SQLException;

public class AddCommand implements DbCommand {
    private final UserDAO userDao;
    private final User user;

    public AddCommand(UserDAO userDao, User user) {
        this.userDao = userDao;
        this.user = user;
    }

    @Override
    public void execute() throws SQLException {
        userDao.addUser(user);
    }
}