package com.example.jdbc.server;

import com.example.jdbc.ConnectionTCP;
import com.example.jdbc.command.Command;
import com.example.jdbc.entity.Student;
import com.example.jdbc.server.repo.StudentRepository;
import com.example.jdbc.server.repo.impl.StudentRepositoryImpl;

import java.net.Socket;
import java.util.List;

public class RequestHandler implements Runnable{

    private final ConnectionTCP connectionTCP;
    public RequestHandler(Socket socket) {
        connectionTCP = new ConnectionTCP(socket);
    }

    @Override
    public void run() {
        StudentRepository studentRepo = new StudentRepositoryImpl();

        while(true) {
            Command command = (Command) connectionTCP.readObject();
            switch (command) {
                case CREATE -> {
                    Student addressBook = (Student) connectionTCP.readObject();
                    studentRepo.save(addressBook);
                }
                case UPDATE -> {
                    Student addressBook = (Student) connectionTCP.readObject();
                    studentRepo.update(addressBook);
                }
                case READ -> {
                    List<Student> addressBooks = studentRepo.gelAll();
                    connectionTCP.writeObject(addressBooks);
                }
                case DELETE -> {
                    System.out.println("Read obj");
                    Integer id = (Integer) connectionTCP.readObject();
                    System.out.println("Read obj" + id);
                    studentRepo.deleteById(id);
                }

                case EXIT -> {
                    connectionTCP.close();
                    System.exit(0);
                }
            }
        }
    }
}
