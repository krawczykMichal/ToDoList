package org.example.todolist.api.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfilesDTO {

    private Integer profilesId;
    private String name;
    private String surname;
    @Email
    private String email;
    private String username;
    private String profilesTasksTitle;
    private String profilesTasksDescription;
    private String profilesTasksIsCompleted;

    @Email
    private String profilesUserEmail;
    private String profilesUserPassword;
    private Boolean profilesUserActive;
    private String profilesRole;
}
