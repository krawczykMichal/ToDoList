package org.example.todolist.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.todolist.infrastructure.security.UserEntity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "tasksId")
@ToString(of = {"tasksId", "title", "description"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class TasksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tasks_id")
    private Integer tasksId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "reminder_datetime")
    private LocalDateTime reminderDatetime;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profiles_id")
    private ProfilesEntity profiles;
}
