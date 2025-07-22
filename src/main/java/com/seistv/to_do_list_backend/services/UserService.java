package com.seistv.to_do_list_backend.services;

import com.seistv.to_do_list_backend.dtos.AddUserRequest;
import com.seistv.to_do_list_backend.dtos.ChangePasswordRequest;
import com.seistv.to_do_list_backend.dtos.UserDto;

public interface UserService {
    UserDto addUser(AddUserRequest request);
    void changePassword(Long userId, ChangePasswordRequest request);
    void deactivateUser(Long userId);
}
