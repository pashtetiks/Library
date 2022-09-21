package com.edu.ulab.app.repository.impl;

import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.storage.Storage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private Storage storage;

    @Override
    public BookEntity save(BookEntity book) {
        return storage.insertBook(book);
    }

    @Override
    public BookEntity update(Long id, BookEntity book) {
        return storage.updateBook(id, book);
    }

    @Override
    public Optional<BookEntity> getById(Long id) {
        return Optional.ofNullable(storage.getBookById(id));

    }

    @Override
    public void deleteById(Long id) {
        storage.deleteBookById(id);
    }
}
