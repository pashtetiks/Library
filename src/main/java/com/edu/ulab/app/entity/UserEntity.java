package com.edu.ulab.app.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class UserEntity {

    private static Long uniqueId = 0L;
    private final Long id;
    private String fullName;
    private String title;
    private int age;
    private List<BookEntity> bookEntities = new ArrayList<>();

    public UserEntity(String fullName, String title, int age) {
        uniqueId++;
        this.fullName = fullName;
        this.title = title;
        this.age = age;
        this.id = uniqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return age == that.age && Objects.equals(fullName, that.fullName) && Objects.equals(title, that.title);
    }
}
