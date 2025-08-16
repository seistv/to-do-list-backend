package com.seistv.to_do_list_backend.auth.controllers;

import com.seistv.to_do_list_backend.auth.dtos.JwtResponse;
import com.seistv.to_do_list_backend.auth.dtos.LoginRequest;
import com.seistv.to_do_list_backend.auth.dtos.LoginResponse;
import com.seistv.to_do_list_backend.auth.jwt.Jwt;
import com.seistv.to_do_list_backend.auth.securities.CookieConfig;
import com.seistv.to_do_list_backend.auth.services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final CookieConfig cookieConfig;

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginRequest request, HttpServletResponse response){
        LoginResponse loginResult = authService.login(request);
        String refreshToken = loginResult.getRefreshToken().toString();
        cookieConfig.setupCookie(response, refreshToken);
        return new JwtResponse(loginResult.getAccessToken().toString());
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@CookieValue(value = "refreshToken") String refreshToken){
        Jwt accessToken = authService.refreshAccessToken(refreshToken);
        return new JwtResponse(accessToken.toString());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
