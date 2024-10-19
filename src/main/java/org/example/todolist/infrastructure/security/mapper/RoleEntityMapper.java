package org.example.todolist.infrastructure.security.mapper;

import org.example.todolist.domain.Role;
import org.example.todolist.infrastructure.security.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {

    Role mapFromEntity(RoleEntity roleEntity);

    RoleEntity mapToEntity(Role role);
}
