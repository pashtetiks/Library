package com.edu.ulab.app.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class BookEntity {

    private static Long uniqueId = 0L;
    private final Long id;
    private Long userId;
    private String title;
    private String author;
    private long pageCount;


    public BookEntity(Long userId, String title, String author, long pageCount) {
        uniqueId++;
        this.userId = userId;
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
        this.id = uniqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return pageCount == that.pageCount && Objects.equals(userId, that.userId) && Objects.equals(title, that.title) && Objects.equals(author, that.author);
    }
}
