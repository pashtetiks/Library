package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.UserDtoToEntityMapper;
import com.edu.ulab.app.repository.UserRepository;
import com.edu.ulab.app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDtoToEntityMapper mapper;
    private final UserRepository repository;

    @Override
    public UserEntity createUser(UserDto userDto) {
        UserEntity userEntity = mapper.userDtoToUserEntity(userDto);
        return repository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserDto userDto) {
        getUserById(userDto.getId());
        UserEntity userEntity = mapper.userDtoToUserEntity(userDto);
        return repository.updateUser(userDto.getId(), userEntity);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("user not founded"));
    }

    @Override
    public void deleteUserById(Long id) {
        getUserById(id);
        repository.deleteById(id);
    }
}
