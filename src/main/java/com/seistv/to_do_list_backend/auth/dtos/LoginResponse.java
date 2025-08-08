package com.seistv.to_do_list_backend.auth.dtos;

import com.seistv.to_do_list_backend.auth.jwt.Jwt;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private Jwt accessToken;
    private Jwt refreshToken;
}
