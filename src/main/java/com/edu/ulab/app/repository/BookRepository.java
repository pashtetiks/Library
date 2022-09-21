package com.edu.ulab.app.repository;

import com.edu.ulab.app.entity.BookEntity;

import java.util.Optional;

public interface BookRepository {

    BookEntity save(BookEntity book);

    BookEntity update(Long id, BookEntity book);

    Optional<BookEntity> getById(Long id);

    void deleteById(Long id);
}
