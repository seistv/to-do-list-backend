package com.seistv.to_do_list_backend.services.impl;

import com.seistv.to_do_list_backend.dtos.UserTaskDto;
import com.seistv.to_do_list_backend.mappers.UserTaskMapper;
import com.seistv.to_do_list_backend.repositories.TaskRepository;
import com.seistv.to_do_list_backend.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserTaskMapper userTaskMapper;

    public List<UserTaskDto> getAllUserTasks() {
        return taskRepository.findAll()
                .stream()
                .map(userTaskMapper::toDto)
                .toList();
    }
}
