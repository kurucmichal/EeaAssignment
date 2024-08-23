package com.kuruc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.kuruc.commands.AddCommand;
import com.kuruc.commands.DbCommand;
import com.kuruc.commands.DeleteAllCommand;
import com.kuruc.commands.PrintAllCommand;
import org.mockito.ArgumentCaptor;

import java.sql.SQLException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MainTest {

    private UserDAO userDao;
    private Producer producer;
    private Consumer consumer;

    @BeforeEach
    public void setUp() {
        userDao = mock(UserDAO.class);
        producer = mock(Producer.class);
        consumer = mock(Consumer.class);
    }

    @Test
    public void testMain() throws SQLException, InterruptedException {
        doNothing().when(producer).produce(any(AddCommand.class));
        doNothing().when(producer).produce(any(PrintAllCommand.class));
        doNothing().when(producer).produce(any(DeleteAllCommand.class));

        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Main main = new Main(userDao, producer, consumer);
        main.run();

        ArgumentCaptor<Object> commandCaptor = ArgumentCaptor.forClass(Object.class);

        verify(producer, times(5)).produce((DbCommand) commandCaptor.capture());

        List<Object> capturedCommands = commandCaptor.getAllValues();
        assert capturedCommands.stream().filter(cmd -> cmd instanceof AddCommand).count() == 2;
        assert capturedCommands.stream().filter(cmd -> cmd instanceof PrintAllCommand).count() == 2;
        assert capturedCommands.stream().filter(cmd -> cmd instanceof DeleteAllCommand).count() == 1;

        consumerThread.interrupt();
        consumerThread.join();
    }
}