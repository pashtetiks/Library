package com.edu.ulab.app.mapper;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDtoToEntityMapper {

    BookEntity bookDtoToBookEntity(BookDto bookDto);

    BookDto bookEntityToBookDto(BookEntity bookEntity);
}
