package com.edu.ulab.app.service;


import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.BookEntity;

public interface BookService {
    BookEntity createBook(BookDto userDto);

    BookEntity updateBook(BookDto userDto);

    BookEntity getBookById(Long id);

    void deleteBookById(Long id);
}
