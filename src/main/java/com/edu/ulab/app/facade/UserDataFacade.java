package com.edu.ulab.app.facade;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.web.request.BookRequest;
import com.edu.ulab.app.web.request.UserBookRequest;
import com.edu.ulab.app.web.request.UserRequest;
import com.edu.ulab.app.web.response.UserBookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class UserDataFacade {
    private final UserService userService;
    private final BookService bookService;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    public UserDataFacade(UserService userService,
                          BookService bookService,
                          UserMapper userMapper,
                          BookMapper bookMapper) {
        this.userService = userService;
        this.bookService = bookService;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
    }

    public UserBookResponse createUserWithBooks(UserBookRequest userBookRequest) {

        UserEntity createdUser = createUser(userBookRequest.getUserRequest());
        log.info("Created user: {}", createdUser);

        List<Long> bookIdList = createBooks(userBookRequest.getBookRequests(), createdUser.getId())
                .stream()
                .map(BookEntity::getId)
                .collect(Collectors.toList());
        log.info("Collected book ids: {}", bookIdList);

        return UserBookResponse.builder()
                .userId(createdUser.getId())
                .booksIdList(bookIdList)
                .build();
    }

    public UserBookResponse updateUserWithBooks(Long userId, UserBookRequest userBookRequest) {
        UserEntity user = userService.getUserById(userId);
        Stream<Long> oldBookIds = user.getBookEntities()
                .stream()
                .map(BookEntity::getId);

        Stream<Long> newBooksId = createBooks(userBookRequest.getBookRequests(), user.getId()).stream()
                .map(BookEntity::getId);

        return UserBookResponse.builder()
                .userId(userId)
                .booksIdList(Stream.concat(oldBookIds, newBooksId).toList())
                .build();
    }

    public UserBookResponse getUserWithBooks(Long userId) {
        UserEntity users = userService.getUserById(userId);
        List<Long> booksId = users.getBookEntities()
                .stream()
                .map(BookEntity::getId)
                .toList();

        return UserBookResponse.builder()
                .userId(userId)
                .booksIdList(booksId)
                .build();
    }

    public void deleteUserWithBooks(Long userId) {
        userService.deleteUserById(userId);
    }

    private UserEntity createUser(UserRequest userRequest) {
        log.info("Got user book create request: {}", userRequest);
        UserDto userDto = userMapper.userRequestToUserDto(userRequest);
        log.info("Mapped user request: {}", userDto);
        return userService.createUser(userDto);
    }

    private List<BookEntity> createBooks(List<BookRequest> bookRequest, Long userId) {
        return bookRequest
                .stream()
                .map(bookMapper::bookRequestToBookDto)
                .peek(bookDto -> bookDto.setUserId(userId))
                .peek(mappedBookDto -> log.info("mapped book: {}", mappedBookDto))
                .map(bookService::createBook)
                .peek(createdBook -> log.info("Created book: {}", createdBook))
                .collect(Collectors.toList());
    }
}
