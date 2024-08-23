package com.kuruc;

import com.kuruc.commands.AddCommand;
import com.kuruc.commands.DeleteAllCommand;
import com.kuruc.commands.PrintAllCommand;
import com.kuruc.domain.User;

import java.sql.SQLException;

public class Main {
    private final UserDAO userDao;
    private final Producer producer;
    private final Consumer consumer;

    public Main(UserDAO userDao, Producer producer, Consumer consumer) {
        this.userDao = userDao;
        this.producer = producer;
        this.consumer = consumer;
    }

    public void run() throws SQLException {
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        producer.produce(new AddCommand(userDao, new User(1, "a1", "Robert")));
        producer.produce(new AddCommand(userDao, new User(2, "a2", "Robert")));
        producer.produce(new PrintAllCommand(userDao));
        producer.produce(new DeleteAllCommand(userDao));
        producer.produce(new PrintAllCommand(userDao));
    }

    public static void main(String[] args) throws SQLException {
        UserDAO userDao = new UserDAO();
        CommandQueue commandQueue = new CommandQueue();
        Producer producer = new Producer(commandQueue);
        Consumer consumer = new Consumer(commandQueue);

        Main main = new Main(userDao, producer, consumer);
        main.run();
    }
}