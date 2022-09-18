package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
public class Storage {

    private final List<UserEntity> users = new ArrayList<>();
    private final List<BookEntity> books = new ArrayList<>();


    public UserEntity selectUserById(Long userId) {
        return users
                .stream()
                .filter(Objects::nonNull)
                .filter(user -> userId.equals(user.getId()))
                .findFirst()
                .orElse(null);
    }

    public UserEntity insertUser(UserEntity user) {
        users.add(user);
        return user;
    }

    public BookEntity insertBook(BookEntity newBook) {
        books.add(newBook);
        UserEntity user = selectUserById(newBook.getUserId());
        user.getBookEntities().add(newBook);
        return newBook;
    }

    public List<UserEntity> selectAllUsers() {
        return users;
    }

}
