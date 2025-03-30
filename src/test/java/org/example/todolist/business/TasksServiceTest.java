package org.example.todolist.business;

import org.example.todolist.api.dto.TasksDTO;
import org.example.todolist.business.dao.TasksDao;
import org.example.todolist.domain.Profiles;
import org.example.todolist.domain.Tasks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TasksServiceTest {

    @Mock
    private TasksDao tasksDao;

    @InjectMocks
    private TasksService tasksService;

    private TasksDTO tasksDTO;
    private Tasks task;
    private Profiles profiles;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Przykładowe dane dla testów
        tasksDTO = new TasksDTO();
        tasksDTO.setTitle("Task 1");
        tasksDTO.setDescription("Description 1");
        tasksDTO.setReminderDatetime(LocalDateTime.now().plusDays(1));
        tasksDTO.setIsCompleted(false);

        profiles = Profiles.builder()
                .profilesId(1)
                .username("john_doe")
                .build();

        task = Tasks.builder()
                .tasksId(1)
                .title("Task 1")
                .description("Description 1")
                .reminderDatetime(LocalDateTime.now().plusDays(1))
                .isCompleted(false)
                .profiles(profiles)
                .build();
    }

    @Test
    void shouldSaveTask() {
        // Given
        when(tasksDao.saveTasks(any(Tasks.class))).thenReturn(task);

        // When
        tasksService.saveTasks(tasksDTO, profiles);

        // Then
        verify(tasksDao, times(1)).saveTasks(any(Tasks.class));
    }

    @Test
    void shouldFindTaskById() {
        // Given
        when(tasksDao.findById(1)).thenReturn(Optional.of(task));

        // When
        Tasks foundTask = tasksService.findById(1);

        // Then
        assertEquals(task, foundTask);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFoundById() {
        // Given
        // Given
        when(tasksDao.findById(anyInt())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NoSuchElementException.class, () -> tasksService.findById(1));

    }

    @Test
    void shouldUpdateTask() {
        // Given
        TasksDTO updatedDTO = new TasksDTO();
        updatedDTO.setTitle("Updated Task");
        updatedDTO.setDescription("Updated Description");
        updatedDTO.setReminderDatetime(LocalDateTime.now().plusDays(2));
        updatedDTO.setIsCompleted(true);

        Tasks updatedTask = task.withTitle(updatedDTO.getTitle())
                .withDescription(updatedDTO.getDescription())
                .withReminderDatetime(updatedDTO.getReminderDatetime())
                .withIsCompleted(updatedDTO.getIsCompleted())
                .withCompletedAt(LocalDateTime.now());

        when(tasksDao.findById(1)).thenReturn(Optional.of(task));
        when(tasksDao.saveTasks(updatedTask)).thenReturn(updatedTask);

        // When
        tasksService.updateTasks(1, updatedDTO);

        // Then
        verify(tasksDao, times(1)).saveTasks(updatedTask);
    }

    @Test
    void shouldDeleteTaskById() {
        // Given
        doNothing().when(tasksDao).deleteById(1);

        // When
        tasksService.deleteById(1);

        // Then
        verify(tasksDao, times(1)).deleteById(1);
    }

    @Test
    void shouldFindTasksByProfilesId() {
        // Given
        when(tasksDao.findByProfilesId(1)).thenReturn(Arrays.asList(task));

        // When
        List<Tasks> tasksList = tasksService.findByProfilesId(1);

        // Then
        assertEquals(1, tasksList.size());
        assertEquals(task, tasksList.get(0));
    }

    @Test
    void shouldDeleteTasksByProfilesId() {
        // Given
        when(tasksDao.findByProfilesId(1)).thenReturn(Arrays.asList(task));
        doNothing().when(tasksDao).deleteById(1);

        // When
        tasksService.deleteByProfilesId(Arrays.asList(task));

        // Then
        verify(tasksDao, times(1)).deleteById(1);
    }

    @Test
    void shouldMarkTaskAsDone() {
            // Given
            Tasks completedTask = task.withCompletedAt(LocalDateTime.now());
            when(tasksDao.findById(1)).thenReturn(Optional.of(task));

            // Mockowanie metody void, która nie zwraca wartości
            doNothing().when(tasksDao).markAsDone(anyInt(), any(LocalDateTime.class));

            // When
            tasksService.markAsDone(1);

            // Then
            ArgumentCaptor<Integer> taskIdCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<LocalDateTime> completedAtCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
            verify(tasksDao, times(1)).markAsDone(taskIdCaptor.capture(), completedAtCaptor.capture());

            // Porównanie argumentów z wykorzystaniem captora (ignorowanie milisekund)
            assertEquals(1, taskIdCaptor.getValue());
            assertTrue(completedAtCaptor.getValue().isBefore(LocalDateTime.now().plusSeconds(1)));  // Porównanie do obecnego czasu z tolerancją
    }

    @Test
    void shouldShowUndoneTasksForProfile() {
        // Given
        when(tasksDao.showTasksByProfilesUsernameUndone("john_doe")).thenReturn(Arrays.asList(task));

        // When
        List<Tasks> tasksList = tasksService.showTasksListForProfilesUsernameUndone("john_doe");

        // Then
        assertEquals(1, tasksList.size());
        assertEquals(task, tasksList.get(0));
    }

    @Test
    void shouldShowDoneTasksForProfile() {
        // Given
        when(tasksDao.showTasksByProfilesUsernameDone("john_doe")).thenReturn(Arrays.asList(task));

        // When
        List<Tasks> tasksList = tasksService.showTasksListForProfilesUsernameDone("john_doe");

        // Then
        assertEquals(1, tasksList.size());
        assertEquals(task, tasksList.get(0));
    }
}