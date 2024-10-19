package org.example.todolist.api.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TasksDTO {

    private Integer tasksId;
    private String title;
    private String description;
    private LocalDateTime reminderDatetime;
    private Boolean isCompleted;
    private LocalDateTime completedAt;
    private Integer tasksProfilesId;
    private String tasksProfilesName;
    private String tasksProfilesSurname;
    private String tasksProfilesEmail;
    private String tasksProfilesUsername;
}
