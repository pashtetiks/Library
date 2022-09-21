package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.BookEntity;
import com.edu.ulab.app.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.Optional.ofNullable;


@Component
public class Storage {

    private final Random random = new Random();

    private final Map<Long, UserEntity> users = new HashMap<>();
    private final Map<Long, BookEntity> books = new HashMap<>();


    public UserEntity selectUserById(Long userId) {
        return users.get(userId);
    }

    public UserEntity insertUser(UserEntity user) {
        user.setId(generateUserId());
        users.put(user.getId(), user);
        return user;
    }

    public BookEntity insertBook(BookEntity newBook) {
        newBook.setId(generateBookId());
        books.put(newBook.getUserId(), newBook);

        UserEntity user = selectUserById(newBook.getUserId());
        user.getBookEntities().add(newBook);
        return newBook;
    }

    public List<UserEntity> selectAllUsers() {
        return users.values().stream().toList();
    }

    private Long generateUserId() {
        Long id = generateRandomPositiveNumber();
        while (users.containsKey(id)) {
            id =generateRandomPositiveNumber();
        }
        return id;
    }

    private Long generateBookId() {
        Long id = generateRandomPositiveNumber();
        while (books.containsKey(id)) {
            id =generateRandomPositiveNumber();
        }
        return id;
    }

    private Long generateRandomPositiveNumber(){
        return random.nextLong(Integer.MAX_VALUE);
    }

    public BookEntity updateBook(Long id, BookEntity updatedBook) {
        Optional<BookEntity> bookEntity = ofNullable(books.get(id));

        if(bookEntity.isEmpty())
            return null;

        boolean isOwnerChanged = !bookEntity.get().getUserId().equals(updatedBook.getUserId());

        if(isOwnerChanged) {
            UserEntity newOwner = users.get(updatedBook.getUserId());
            newOwner.getBookEntities().add(updatedBook);

            UserEntity oldOwner = users.get(bookEntity.get().getUserId());
            oldOwner.getBookEntities().remove(bookEntity.get());
        }

        books.put(id, updatedBook);
        return updatedBook;
    }

    public void deleteBookById(Long id) {
        Optional<BookEntity> bookEntity = ofNullable(books.get(id));

        if(bookEntity.isEmpty())
            return;

        Long userId = bookEntity.get().getUserId();
        users.get(userId).getBookEntities().remove(bookEntity.get());
        books.remove(id);
    }

    public UserEntity updateUser(Long id, UserEntity userEntity) {
        Optional<UserEntity> user = ofNullable(users.get(id));

        if(user.isEmpty())
            return null;

        userEntity.setBookEntities(user.get().getBookEntities());

        users.put(id, userEntity);
        return userEntity;
    }

    public void deleteUserById(Long id) {
        Optional<UserEntity> user = ofNullable(users.get(id));

        if(user.isEmpty())
            return;

        List<Long> booksIdForRemove = books.entrySet().stream()
                .filter(entry -> entry.getValue().getUserId().equals(id))
                .map(Map.Entry::getKey)
                .toList();

       users.remove(id);
       booksIdForRemove.forEach(books.keySet()::remove);
    }

    public BookEntity getBookById(Long id) {
        return books.get(id);
    }
}
