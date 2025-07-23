package com.seistv.to_do_list_backend.dtos;

import com.seistv.to_do_list_backend.validations.Lowercase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddUserRequest {
    @NotBlank(message = "Username is required.")
    @Pattern(
            regexp = "^(?![_.])(?!.*[_.]{2})[a-z0-9._-]{4,35}(?<![_.])$",
            message = "Username must be 4–35 lowercase characters that doesn't start/end with '_' or '.'"
    )
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 25, message = "Password must be between 8 to 25 characters long.")
    private String password;
}
