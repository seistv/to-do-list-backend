package com.seistv.to_do_list_backend.mappers;

import com.seistv.to_do_list_backend.dtos.AddUserRequest;
import com.seistv.to_do_list_backend.dtos.UserDto;
import com.seistv.to_do_list_backend.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(AddUserRequest request);
}
