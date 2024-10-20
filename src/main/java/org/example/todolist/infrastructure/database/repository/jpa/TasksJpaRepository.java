package org.example.todolist.infrastructure.database.repository.jpa;

import org.example.todolist.domain.Tasks;
import org.example.todolist.infrastructure.database.entity.TasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TasksJpaRepository extends JpaRepository<TasksEntity, Integer> {

    @Query("select tas from TasksEntity tas join tas.profiles pro where pro.username = :username and tas.isCompleted = false")
    List<TasksEntity> findByProfilesUsernameUndone(String username);

    @Query("select tas from TasksEntity tas join tas.profiles pro where pro.username = :username and tas.isCompleted = true")
    List<TasksEntity> findByProfilesUsernameDone(String username);

    @Query("select tas from TasksEntity tas join tas.profiles pro where pro.profilesId = :profilesId")
    List<TasksEntity> findByProfilesId(Integer profilesId);

    @Modifying
    @Query("update TasksEntity tas set tas.isCompleted = true, tas.completedAt = :completedAt where tas.tasksId = :tasksId")
    void markAsDone(Integer tasksId, LocalDateTime completedAt);
}