package org.example.todolist.infrastructure.database.repository.mapper;


import org.example.todolist.domain.Profiles;
import org.example.todolist.infrastructure.database.entity.ProfilesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfilesEntityMapper {

    @Mapping(target = "tasks", ignore = true)
    Profiles mapFromProfilesEntity(ProfilesEntity profilesEntity);

    ProfilesEntity mapToProfilesEntity(Profiles profiles);
}
