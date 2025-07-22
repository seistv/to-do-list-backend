package com.seistv.to_do_list_backend.services;

import com.seistv.to_do_list_backend.dtos.UserTaskDto;

import java.util.List;

public interface TaskService {
    List<UserTaskDto> getAllUserTasks();
}
