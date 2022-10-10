package com.example.jdbc.server.repo;

import com.example.jdbc.entity.AddressBook;

import java.util.List;

public interface BookRepository {

    List<AddressBook> gelAllById();

    void deleteById(Integer id);

    void save(AddressBook addressBook);

    void update(AddressBook addressBook);

}
