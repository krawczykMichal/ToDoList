package org.example.todolist.business.dao;

import org.example.todolist.domain.Tasks;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TasksDao {

    Tasks saveTasks(Tasks tasks);

    List<Tasks> showTasksByProfilesUsernameUndone(String username);

    List<Tasks> showTasksByProfilesUsernameDone(String username);

    Optional<Tasks> findById(Integer tasksId);

    void deleteById(Integer tasksId);

    List<Tasks> findByProfilesId(Integer profilesId);

    void markAsDone(Integer tasks, LocalDateTime completedAt);
}
