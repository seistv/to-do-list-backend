package com.seistv.to_do_list_backend.auth.securities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seistv.to_do_list_backend.auth.dtos.JwtResponse;
import com.seistv.to_do_list_backend.auth.jwt.Jwt;
import com.seistv.to_do_list_backend.auth.jwt.JwtService;
import com.seistv.to_do_list_backend.user.entities.User;
import com.seistv.to_do_list_backend.user.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CookieConfig cookieConfig;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setPassword("");
                    return userRepository.save(newUser);
                });

        Jwt accessToken = jwtService.generateAccessToken(user);
        Jwt refreshToken = jwtService.generateRefreshToken(user);

        cookieConfig.setupCookie(response, refreshToken.toString());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JwtResponse jwtResponse = new JwtResponse(accessToken.toString());
        new ObjectMapper().writeValue(response.getWriter(), jwtResponse);
    }
}
