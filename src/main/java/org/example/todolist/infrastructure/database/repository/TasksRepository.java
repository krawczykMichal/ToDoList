package org.example.todolist.infrastructure.database.repository;


import lombok.AllArgsConstructor;
import org.example.todolist.business.dao.TasksDao;
import org.example.todolist.domain.Tasks;
import org.example.todolist.infrastructure.database.entity.TasksEntity;
import org.example.todolist.infrastructure.database.repository.jpa.TasksJpaRepository;
import org.example.todolist.infrastructure.database.repository.mapper.TasksEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TasksRepository implements TasksDao {

    private final TasksJpaRepository tasksJpaRepository;

    private final TasksEntityMapper tasksEntityMapper;

    @Override
    public Tasks saveTasks(Tasks tasks) {
        TasksEntity toSave = tasksEntityMapper.mapToTasksEntity(tasks);
        TasksEntity saved = tasksJpaRepository.save(toSave);

        return tasksEntityMapper.mapFromTasksEntity(saved);
    }

    @Override
    public List<Tasks> showTasksByProfilesUsernameUndone(String username) {
        List<TasksEntity> tasksEntityList = tasksJpaRepository.findByProfilesUsernameUndone(username);
        return tasksEntityList.stream().map(tasksEntityMapper::mapFromTasksEntity).toList();
    }
    @Override
    public List<Tasks> showTasksByProfilesUsernameDone(String username) {
        List<TasksEntity> tasksEntityList = tasksJpaRepository.findByProfilesUsernameDone(username);
        return tasksEntityList.stream().map(tasksEntityMapper::mapFromTasksEntity).toList();
    }

    @Override
    public Optional<Tasks> findById(Integer tasksId) {
        return tasksJpaRepository.findById(tasksId).map(tasksEntityMapper::mapFromTasksEntity);
    }

    @Override
    public void deleteById(Integer tasksId) {
        tasksJpaRepository.deleteById(tasksId);
    }

    @Override
    public List<Tasks> findByProfilesId(Integer profilesId) {
        List<TasksEntity> tasksEntityList = tasksJpaRepository.findByProfilesId(profilesId);
        return tasksEntityList.stream().map(tasksEntityMapper::mapFromTasksEntity).collect(Collectors.toList());
    }
}
