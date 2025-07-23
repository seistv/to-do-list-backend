package com.seistv.to_do_list_backend.mappers;

import com.seistv.to_do_list_backend.dtos.UserTaskDto;
import com.seistv.to_do_list_backend.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserTaskMapper {
    @Mapping(source = "id", target = "taskId")
    @Mapping(source = "task.user.id", target = "userId")
    @Mapping(source = "task.user.username", target = "username")
    @Mapping(source = "task.user.deactivated", target = "isDeactivated")
    UserTaskDto toDto(Task task);
}
