package com.seistv.to_do_list_backend.user.controllers;

import com.seistv.to_do_list_backend.auth.dtos.JwtResponse;
import com.seistv.to_do_list_backend.auth.jwt.Jwt;
import com.seistv.to_do_list_backend.auth.jwt.JwtService;
import com.seistv.to_do_list_backend.auth.securities.CookieConfig;
import com.seistv.to_do_list_backend.user.dtos.AddUserRequest;
import com.seistv.to_do_list_backend.user.dtos.ChangePasswordRequest;
import com.seistv.to_do_list_backend.user.dtos.UserDto;
import com.seistv.to_do_list_backend.user.entities.User;
import com.seistv.to_do_list_backend.user.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final CookieConfig cookieConfig;

    @PostMapping
    public JwtResponse addUser(@Valid @RequestBody AddUserRequest request,
                               HttpServletResponse response) {
        UserDto userDto = userService.addUser(request);
        User user = userService.findById(userDto.getId());
        Jwt accessToken = jwtService.generateAccessToken(user);
        Jwt refreshToken = jwtService.generateRefreshToken(user);
        cookieConfig.setupCookie(response, refreshToken.toString());
        return new JwtResponse(accessToken.toString());
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