package com.edu.ulab.app.validation;

import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.exception.DuplicationException;
import com.edu.ulab.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@AllArgsConstructor
public class UserValidator {
    private final UserRepository repository;

    public void validateUniqueUser(UserEntity user){
        List<UserEntity> users = repository.findAll();
        boolean isAlreadyExist = users
                .stream()
                .anyMatch(user::equals);
        if (isAlreadyExist){
            throw new DuplicationException("user already exist");
        }
    }
}
