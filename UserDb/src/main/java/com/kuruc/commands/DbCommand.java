package com.kuruc.commands;

import java.sql.SQLException;

public interface DbCommand {
    void execute() throws SQLException;
}