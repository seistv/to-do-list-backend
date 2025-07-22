package com.seistv.to_do_list_backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddUserRequest {
    @NotBlank(message = "Username is required.")
    @Pattern(
            regexp = "^(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._-]{4,35}(?<![_.])$",
            message = "Username must be 4–35 characters, only a-z, A-Z, 0-9, " +
                    "'_', '-', '.', no starting/ending with '_' or '.'"
    )
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 25, message = "Password must be between 8 to 25 characters long.")
    private String password;
}
