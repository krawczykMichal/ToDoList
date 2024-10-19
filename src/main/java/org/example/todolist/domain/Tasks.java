package org.example.todolist.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "tasksId")
@ToString(of = {"tasksId", "title", "description", "reminderDatetime"})
public class Tasks {

    Integer tasksId;
    String title;
    String description;
    LocalDateTime reminderDatetime;
    Boolean isCompleted;
    LocalDateTime completedAt;
    Profiles profiles;
}
