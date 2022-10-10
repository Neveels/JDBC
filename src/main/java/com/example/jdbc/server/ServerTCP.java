package com.example.jdbc.server;

import com.example.jdbc.entity.AddressBook;
import com.example.jdbc.server.repo.impl.BookRepositoryImpl;

import java.util.List;

public class ServerTCP {
    public static void main(String[] args) {

        AddressBook addressBook =  new AddressBook(1, 4, "Belarus","Ilyasd", "qqq","neevels@mail.ru","+6464644");

        BookRepositoryImpl bookRepository = new BookRepositoryImpl();
        List<AddressBook> addressBooks = bookRepository.gelAllById();

        bookRepository.deleteById(1);
        System.out.println(addressBooks);

        bookRepository.save(addressBook);
        System.out.println(bookRepository.gelAllById());
    }
}
