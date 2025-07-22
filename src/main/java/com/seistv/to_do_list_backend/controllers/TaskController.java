package com.seistv.to_do_list_backend.controllers;

import com.seistv.to_do_list_backend.dtos.UserTaskDto;
import com.seistv.to_do_list_backend.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/user-tasks")
    public List<UserTaskDto> getAllUserTasks() {
        return taskService.getAllUserTasks();
    }
}
