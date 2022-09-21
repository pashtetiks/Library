package com.edu.ulab.app.repository.impl;

import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.repository.UserRepository;
import com.edu.ulab.app.storage.Storage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private Storage storage;

    @Override
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(storage.selectUserById(id));
    }

    @Override
    public UserEntity save(UserEntity user) {
        return storage.insertUser(user);
    }

    @Override
    public void deleteById(Long id) {
        storage.deleteUserById(id);
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity userEntity) {
        return storage.updateUser(id, userEntity);
    }
}
