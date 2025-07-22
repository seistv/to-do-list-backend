package com.seistv.to_do_list_backend.controllers;

import com.seistv.to_do_list_backend.dtos.AddUserRequest;
import com.seistv.to_do_list_backend.dtos.ChangePasswordRequest;
import com.seistv.to_do_list_backend.dtos.UserDto;
import com.seistv.to_do_list_backend.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody AddUserRequest request,
                                     UriComponentsBuilder uriBuilder) {
        UserDto userDto = userService.addUser(request);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PostMapping("/{id}/change-password")
    public void changePassword(@PathVariable Long id,
                               @Valid @RequestBody ChangePasswordRequest request) {
        userService.changePassword(id, request);
    }

    @DeleteMapping("/{id}")
    public void deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
    }
}