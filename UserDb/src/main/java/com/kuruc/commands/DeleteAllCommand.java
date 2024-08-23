package com.kuruc.commands;

import com.kuruc.UserDAO;

import java.sql.SQLException;

public class DeleteAllCommand implements DbCommand {
    private final UserDAO userDao;

    public DeleteAllCommand(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public void execute() throws SQLException {
        userDao.deleteAllUsers();
        System.out.println("All users deleted successfully!");
    }
}
