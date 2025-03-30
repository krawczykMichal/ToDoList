package org.example.todolist.infrastructure.database.repository;

import org.example.todolist.domain.Tasks;
import org.example.todolist.infrastructure.database.entity.TasksEntity;
import org.example.todolist.infrastructure.database.repository.jpa.TasksJpaRepository;
import org.example.todolist.infrastructure.database.repository.mapper.TasksEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TasksRepositoryTest {

    @Mock
    private TasksJpaRepository tasksJpaRepository;

    @Mock
    private TasksEntityMapper tasksEntityMapper;

    @InjectMocks
    private TasksRepository tasksRepository;

    private Tasks tasks;
    private TasksEntity tasksEntity;

    @BeforeEach
    void setUp() {
        tasks = Tasks.builder()
                .tasksId(1)
                .title("Task 1")
                .description("Description")
                .completedAt(LocalDateTime.now())
                .isCompleted(false)
                .build();

        tasksEntity = TasksEntity.builder()
                .tasksId(1)
                .title("Task 1")
                .description("Description")
                .completedAt(LocalDateTime.now())
                .isCompleted(false)
                .build();
    }

    @Test
    void shouldSaveTasks() {
        // Given
        when(tasksEntityMapper.mapToTasksEntity(tasks)).thenReturn(tasksEntity);
        when(tasksJpaRepository.save(tasksEntity)).thenReturn(tasksEntity);
        when(tasksEntityMapper.mapFromTasksEntity(tasksEntity)).thenReturn(tasks);

        // When
        Tasks savedTask = tasksRepository.saveTasks(tasks);

        // Then
        assertNotNull(savedTask);
        assertEquals("Task 1", savedTask.getTitle());
        verify(tasksJpaRepository, times(1)).save(tasksEntity);
        verify(tasksEntityMapper, times(1)).mapToTasksEntity(tasks);
        verify(tasksEntityMapper, times(1)).mapFromTasksEntity(tasksEntity);
    }

    @Test
    void shouldShowTasksByProfilesUsernameUndone() {
        // Given
        List<TasksEntity> tasksEntityList = List.of(tasksEntity);
        when(tasksJpaRepository.findByProfilesUsernameUndone("john_doe")).thenReturn(tasksEntityList);
        when(tasksEntityMapper.mapFromTasksEntity(tasksEntity)).thenReturn(tasks);

        // When
        List<Tasks> tasksList = tasksRepository.showTasksByProfilesUsernameUndone("john_doe");

        // Then
        assertNotNull(tasksList);
        assertEquals(1, tasksList.size());
        assertEquals("Task 1", tasksList.get(0).getTitle());
        verify(tasksJpaRepository, times(1)).findByProfilesUsernameUndone("john_doe");
        verify(tasksEntityMapper, times(1)).mapFromTasksEntity(tasksEntity);
    }

    @Test
    void shouldShowTasksByProfilesUsernameDone() {
        // Given
        List<TasksEntity> tasksEntityList = List.of(tasksEntity);
        when(tasksJpaRepository.findByProfilesUsernameDone("john_doe")).thenReturn(tasksEntityList);
        when(tasksEntityMapper.mapFromTasksEntity(tasksEntity)).thenReturn(tasks);

        // When
        List<Tasks> tasksList = tasksRepository.showTasksByProfilesUsernameDone("john_doe");

        // Then
        assertNotNull(tasksList);
        assertEquals(1, tasksList.size());
        assertEquals("Task 1", tasksList.get(0).getTitle());
        verify(tasksJpaRepository, times(1)).findByProfilesUsernameDone("john_doe");
        verify(tasksEntityMapper, times(1)).mapFromTasksEntity(tasksEntity);
    }

    @Test
    void shouldFindById() {
        // Given
        when(tasksJpaRepository.findById(1)).thenReturn(Optional.of(tasksEntity));
        when(tasksEntityMapper.mapFromTasksEntity(tasksEntity)).thenReturn(tasks);

        // When
        Optional<Tasks> foundTask = tasksRepository.findById(1);

        // Then
        assertTrue(foundTask.isPresent());
        assertEquals("Task 1", foundTask.get().getTitle());
        verify(tasksJpaRepository, times(1)).findById(1);
        verify(tasksEntityMapper, times(1)).mapFromTasksEntity(tasksEntity);
    }

    @Test
    void shouldDeleteById() {
        // Given
        doNothing().when(tasksJpaRepository).deleteById(1);

        // When
        tasksRepository.deleteById(1);

        // Then
        verify(tasksJpaRepository, times(1)).deleteById(1);
    }

    @Test
    void shouldMarkAsDone() {
        // Given
        LocalDateTime completedAt = LocalDateTime.now();
        doNothing().when(tasksJpaRepository).markAsDone(1, completedAt);

        // When
        tasksRepository.markAsDone(1, completedAt);

        // Then
        verify(tasksJpaRepository, times(1)).markAsDone(1, completedAt);
    }
}