package org.example.todolist.business;

import lombok.AllArgsConstructor;
import org.example.todolist.api.dto.TasksDTO;
import org.example.todolist.business.dao.TasksDao;
import org.example.todolist.domain.Profiles;
import org.example.todolist.domain.Tasks;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TasksService {

    private final TasksDao tasksDao;


    @Transactional
    public void saveTasks(TasksDTO tasksDTO, Profiles profilesByUsername) {
        Tasks tasks = createTasks(tasksDTO);
        Tasks tasks1 = tasks.withProfiles(profilesByUsername);

        tasksDao.saveTasks(tasks1);
    }

    private Tasks createTasks(TasksDTO tasksDTO) {

        return Tasks.builder()
                .title(tasksDTO.getTitle())
                .description(tasksDTO.getDescription())
                .reminderDatetime(tasksDTO.getReminderDatetime())
                .isCompleted(false)
                .build();
    }


    public List<Tasks> showTasksListForProfilesUsernameUndone(String username) {
        return tasksDao.showTasksByProfilesUsernameUndone(username);
    }
    public List<Tasks> showTasksListForProfilesUsernameDone(String username) {
        return tasksDao.showTasksByProfilesUsernameDone(username);
    }

    public Tasks findById(Integer tasksId) {
        Optional<Tasks> tasks = tasksDao.findById(tasksId);
        return tasks.get();
    }

    @Transactional
    public void updateTasks(Integer tasksId, TasksDTO tasksDTO) {
        Tasks tasksToUpdate = findById(tasksId);

        Tasks updatedTasks = tasksToUpdate.withTitle(tasksDTO.getTitle())
                .withDescription(tasksDTO.getDescription())
                .withReminderDatetime(tasksDTO.getReminderDatetime())
                .withIsCompleted(tasksDTO.getIsCompleted())
                .withCompletedAt(LocalDateTime.now());

        tasksDao.saveTasks(updatedTasks);
    }

    @Transactional
    public void deleteById(Integer tasksId) {
        tasksDao.deleteById(tasksId);
    }

    @Transactional
    public List<Tasks> findByProfilesId(Integer profilesId) {
        return tasksDao.findByProfilesId(profilesId);
    }

    @Transactional
    public void deleteByProfilesId(List<Tasks> tasksByProfilesId) {
        for (Tasks tasks : tasksByProfilesId) {
            tasksDao.deleteById(tasks.getTasksId());
        }
    }
}
