package com.kuruc;

import com.kuruc.commands.DbCommand;

public class Producer {
    private final CommandQueue commandQueue;

    public Producer(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

    public void produce(DbCommand command) {
        commandQueue.addCommand(command);
    }
}