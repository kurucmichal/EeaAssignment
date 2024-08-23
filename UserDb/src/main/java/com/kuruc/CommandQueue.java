package com.kuruc;

import com.kuruc.commands.DbCommand;

import java.util.LinkedList;
import java.util.Queue;

public class CommandQueue {
    private final Queue<DbCommand> queue = new LinkedList<>();

    public synchronized void addCommand(DbCommand command) {
        queue.add(command);
        notify();
    }

    public synchronized DbCommand getCommand() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.poll();
    }
}