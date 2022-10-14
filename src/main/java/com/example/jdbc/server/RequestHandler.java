package com.example.jdbc.server;

import com.example.jdbc.ConnectionTCP;
import com.example.jdbc.command.Command;
import com.example.jdbc.entity.AddressBook;
import com.example.jdbc.server.repo.BookRepository;
import com.example.jdbc.server.repo.impl.BookRepositoryImpl;

import java.net.Socket;
import java.util.List;

public class RequestHandler implements Runnable{

    private final ConnectionTCP connectionTCP;
    public RequestHandler(Socket socket) {
        connectionTCP = new ConnectionTCP(socket);
    }

    @Override
    public void run() {
        BookRepository bookRepository = new BookRepositoryImpl();

        while(true) {
            Command command = (Command) connectionTCP.readObject();
            switch (command) {
                case CREATE -> {
                    AddressBook addressBook = (AddressBook) connectionTCP.readObject();
                    bookRepository.save(addressBook);
                }
                case UPDATE -> {
                    AddressBook addressBook = (AddressBook) connectionTCP.readObject();
                    bookRepository.update(addressBook);
                }
                case READ -> {
                    List<AddressBook> addressBooks = bookRepository.gelAll();
                    connectionTCP.writeObject(addressBooks);
                }
                case DELETE -> {
                    System.out.println("Read obj");
                    Integer id = (Integer) connectionTCP.readObject();
                    System.out.println("Read obj" + id);
                    bookRepository.deleteById(id);
                }

                case EXIT -> {
                    connectionTCP.close();
                    System.exit(0);
                }
            }
        }
    }
}
