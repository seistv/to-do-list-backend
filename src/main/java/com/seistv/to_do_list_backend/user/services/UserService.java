package com.seistv.to_do_list_backend.user.services;

import com.seistv.to_do_list_backend.user.dtos.AddUserRequest;
import com.seistv.to_do_list_backend.user.dtos.ChangePasswordRequest;
import com.seistv.to_do_list_backend.user.dtos.UserDto;

public interface UserService {
    UserDto addUser(AddUserRequest request);
    void changePassword(Long userId, ChangePasswordRequest request);
    void deactivateUser(Long userId);
}
