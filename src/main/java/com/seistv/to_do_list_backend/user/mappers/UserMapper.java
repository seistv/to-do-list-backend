package com.seistv.to_do_list_backend.user.mappers;

import com.seistv.to_do_list_backend.user.dtos.AddUserRequest;
import com.seistv.to_do_list_backend.user.dtos.UserDto;
import com.seistv.to_do_list_backend.user.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(AddUserRequest request);
}
