package com.seistv.to_do_list_backend.services.impl;

import com.seistv.to_do_list_backend.dtos.AddUserRequest;
import com.seistv.to_do_list_backend.dtos.ChangePasswordRequest;
import com.seistv.to_do_list_backend.dtos.UserDto;
import com.seistv.to_do_list_backend.exceptions.DuplicateUserException;
import com.seistv.to_do_list_backend.exceptions.InvalidPasswordException;
import com.seistv.to_do_list_backend.exceptions.UserNotFoundException;
import com.seistv.to_do_list_backend.mappers.UserMapper;
import com.seistv.to_do_list_backend.models.User;
import com.seistv.to_do_list_backend.repositories.UserRepository;
import com.seistv.to_do_list_backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto addUser(AddUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateUserException();
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void changePassword(Long userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public void deactivateUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        user.setDeactivated(!user.isDeactivated());
        userRepository.save(user);
    }
}
