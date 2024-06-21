package com.saga.crm.service;

import com.saga.crm.dto.UserDto;
import com.saga.crm.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
    void deleteUser(Long id); // Novo método
    void updateUserProfile(UserDto userDto);
    void updatePassword(String email, String newPassword);
    boolean isPasswordMatches(User user, String currentPassword);
}