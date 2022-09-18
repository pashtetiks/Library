package com.edu.ulab.app.service;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;

public interface UserService {
    UserEntity createUser(UserDto userDto);

    UserEntity updateUser(UserDto userDto);

    UserEntity getUserById(Long id);

    void deleteUserById(Long id);
}
