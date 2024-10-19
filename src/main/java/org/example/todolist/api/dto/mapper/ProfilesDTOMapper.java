package org.example.todolist.api.dto.mapper;

import org.example.todolist.api.dto.ProfilesDTO;
import org.example.todolist.domain.Profiles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProfilesDTOMapper {

    @Mappings({
            @Mapping(source = "user.email", target = "profilesUserEmail", ignore = true),
            @Mapping(source = "user.password", target = "profilesUserPassword", ignore = true),
            @Mapping(source = "user.active", target = "profilesUserActive", ignore = true),
            @Mapping(source = "role.role", target = "profilesRole", ignore = true)

    })
    ProfilesDTO map(Profiles profiles);


    Profiles map(ProfilesDTO profilesDTO);
}
