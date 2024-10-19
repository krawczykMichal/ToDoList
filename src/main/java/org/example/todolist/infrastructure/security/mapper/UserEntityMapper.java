package org.example.todolist.infrastructure.security.mapper;

import org.example.todolist.domain.User;
import org.example.todolist.infrastructure.security.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    User mapFromEntity(UserEntity userEntity);

    UserEntity mapToEntity(User user);
}
