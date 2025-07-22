package com.seistv.to_do_list_backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String username;
    private boolean isDeactivated;

    @JsonProperty("isDeactivated")
    public boolean isDeactivated() {
        return isDeactivated;
    }
}
