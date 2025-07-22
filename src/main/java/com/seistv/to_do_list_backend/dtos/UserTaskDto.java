package com.seistv.to_do_list_backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserTaskDto {
    private Long userId;
    private String username;
    private boolean isDeactivated;
    private Long taskId;
    private String task;
    private int order;

    @JsonProperty("isDeactivated")
    public boolean isDeactivated() {
        return isDeactivated;
    }
}
