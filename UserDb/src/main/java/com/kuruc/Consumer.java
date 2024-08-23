package com.kuruc;

import com.kuruc.commands.AddCommand;
import com.kuruc.commands.DbCommand;
import com.kuruc.commands.DeleteAllCommand;
import com.kuruc.commands.PrintAllCommand;
import com.kuruc.exceptions.InvalidCommandException;

import java.sql.SQLException;

public class Consumer implements Runnable {
    private final CommandQueue commandQueue;

    public Consumer(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DbCommand command = commandQueue.getCommand();
                if (!(command instanceof PrintAllCommand || command instanceof DeleteAllCommand || command instanceof AddCommand)) {
                    throw new InvalidCommandException("Invalid command type: " + command.getClass().getName());
                }
                command.execute();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (SQLException | InvalidCommandException e) {
                e.printStackTrace();
            }
        }
    }
}