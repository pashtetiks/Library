package com.edu.ulab.app.repository;

import com.edu.ulab.app.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findById(Long id);

    UserEntity save(UserEntity user);

    void deleteById(Long id);

    UserEntity updateUser(Long id, UserEntity userEntity);

}
