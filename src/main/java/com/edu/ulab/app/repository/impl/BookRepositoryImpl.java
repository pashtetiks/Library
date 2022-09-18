package com.edu.ulab.app.repository.impl;

import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.storage.Storage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private Storage storage;

    @Override
    public BookEntity save(BookEntity book) {
        return storage.insertBook(book);
    }
}
