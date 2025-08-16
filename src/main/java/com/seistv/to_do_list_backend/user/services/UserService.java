package com.seistv.to_do_list_backend.user.services;

import com.seistv.to_do_list_backend.user.dtos.AddUserRequest;
import com.seistv.to_do_list_backend.user.dtos.ChangePasswordRequest;
import com.seistv.to_do_list_backend.user.dtos.UserDto;
import com.seistv.to_do_list_backend.common.exceptions.DuplicateUserException;
import com.seistv.to_do_list_backend.common.exceptions.InvalidPasswordException;
import com.seistv.to_do_list_backend.common.exceptions.UserNotFoundException;
import com.seistv.to_do_list_backend.user.mappers.UserMapper;
import com.seistv.to_do_list_backend.user.entities.User;
import com.seistv.to_do_list_backend.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto addUser(AddUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
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

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
