package com.seistv.to_do_list_backend.mappers;

import com.seistv.to_do_list_backend.dtos.UserTaskDto;
import com.seistv.to_do_list_backend.task.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserTaskMapper {
    @Mapping(source = "id", target = "taskId")
    @Mapping(source = "task.user.id", target = "userId")
    @Mapping(source = "task.user.email", target = "email")
    @Mapping(source = "task.user.deactivated", target = "isDeactivated")
    @Mapping(source = "done", target = "isDone")
    UserTaskDto toDto(Task task);
}
