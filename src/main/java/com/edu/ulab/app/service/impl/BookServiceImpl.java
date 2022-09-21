package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.BookDtoToEntityMapper;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDtoToEntityMapper mapper;
    private final BookRepository repository;

    @Override
    public BookEntity createBook(BookDto bookDto) {
        BookEntity bookEntity = mapper.bookDtoToBookEntity(bookDto);
        return repository.save(bookEntity);
    }

    @Override
    public BookEntity updateBook(BookDto bookDto) {
        getBookById(bookDto.getId());
        BookEntity bookEntity = mapper.bookDtoToBookEntity(bookDto);
        return repository.update(bookDto.getId(), bookEntity);
    }

    @Override
    public BookEntity getBookById(Long id) {
        return repository.getById(id)
                .orElseThrow(() -> new NotFoundException("book not founded"));
    }

    @Override
    public void deleteBookById(Long id) {
        repository.deleteById(id);
    }
}
