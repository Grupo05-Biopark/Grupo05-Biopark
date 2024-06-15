package com.saga.crm.service;

import com.saga.crm.dto.UserDto;
import com.saga.crm.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
