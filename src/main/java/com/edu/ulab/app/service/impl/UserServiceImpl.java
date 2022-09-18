package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.UserDtoToEntityMapper;
import com.edu.ulab.app.repository.UserRepository;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.validation.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserValidator validator;
    private final UserDtoToEntityMapper mapper;
    private final UserRepository repository;

    @Override
    public UserEntity createUser(UserDto userDto) {
        UserEntity userEntity = mapper.userDtoToUserEntity(userDto);
        validator.validateUniqueUser(userEntity);
        return repository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserEntity getUserById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not founded"));
    }

    @Override
    public void deleteUserById(Long id) {
    }
}
