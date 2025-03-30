package org.example.todolist.business;

import org.example.todolist.api.dto.ProfilesDTO;
import org.example.todolist.api.dto.mapper.ProfilesDTOMapper;
import org.example.todolist.business.dao.ProfilesDao;
import org.example.todolist.domain.Profiles;
import org.example.todolist.domain.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfilesServiceTest {

    @Mock
    private ProfilesDao profilesDao;

    @Mock
    private TasksService tasksService;

    @Mock
    private ProfilesDTOMapper profilesDTOMapper;

    @InjectMocks
    private ProfilesService profilesService;

    private ProfilesDTO profilesDTO;
    private Profiles profiles;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Przykładowe dane dla testu
        profilesDTO = new ProfilesDTO();
        profilesDTO.setName("John");
        profilesDTO.setSurname("Doe");
        profilesDTO.setEmail("john.doe@example.com");
        profilesDTO.setUsername("john_doe");
        profilesDTO.setProfilesUserPassword("password");

        profiles = Profiles.builder()
                .profilesId(1)
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .username("john_doe")
                .build();
    }

    @Test
    void findProfilesById() {
        // Given
        when(profilesDao.findById(1)).thenReturn(Optional.of(profiles));

        // When
        Profiles foundProfile = profilesService.findProfilesById(1);

        // Then
        assertEquals(profiles, foundProfile);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenProfilesByIdNotFound() {
        // Given
        when(profilesDao.findById(1)).thenReturn(Optional.empty());

        // When & Then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            profilesService.findProfilesById(1);
        });
        assertEquals("Could not find profile with id: 1", exception.getMessage());
    }

    @Test
    void shouldFindProfilesByEmail() {
        // Given
        when(profilesDao.findByEmail("john.doe@example.com")).thenReturn(Optional.of(profiles));

        // When
        Profiles foundProfile = profilesService.findProfilesByEmail("john.doe@example.com");

        // Then
        assertEquals(profiles, foundProfile);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenProfilesByEmailNotFound() {
        // Given
        when(profilesDao.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());

        // When & Then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            profilesService.findProfilesByEmail("john.doe@example.com");
        });
        assertEquals("Could not find profile with email: john.doe@example.com", exception.getMessage());
    }

    @Test
    void shouldFindProfilesByUsername() {
        // Given
        when(profilesDao.findByUsername("john_doe")).thenReturn(Optional.of(profiles));

        // When
        Profiles foundProfile = profilesService.findProfilesByUsername("john_doe");

        // Then
        assertEquals(profiles, foundProfile);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenProfilesByUsernameNotFound() {
        // Given
        when(profilesDao.findByUsername("john_doe")).thenReturn(Optional.empty());

        // When & Then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            profilesService.findProfilesByUsername("john_doe");
        });
        assertEquals("Could not find profile with username: john_doe", exception.getMessage());
    }

    @Test
    void shouldSaveProfiles() {
        // Given
        Profiles profilesToSave = Profiles.builder()
                .name(profilesDTO.getName())
                .surname(profilesDTO.getSurname())
                .email(profilesDTO.getEmail())
                .username(profilesDTO.getUsername())
                .build();
        when(profilesDao.saveProfiles(profilesToSave)).thenReturn(profiles);

        // When
        profilesService.saveProfiles(profilesDTO);

        // Then
        verify(profilesDao, times(1)).saveProfiles(profilesToSave);
    }

    @Test
    void shouldUpdateProfiles() {
        // Given
        Profiles updatedProfile = profiles.withEmail("new.email@example.com")
                .withName("John Updated")
                .withSurname("Doe Updated");

        when(profilesDao.findById(1)).thenReturn(Optional.of(profiles));
        when(profilesDao.saveProfiles(updatedProfile)).thenReturn(updatedProfile);

        // When
        profilesService.updateProfiles(1, profilesDTO);

        // Then
        verify(profilesDao, times(1)).saveProfiles(updatedProfile);
    }

    @Test
    void shouldDeleteProfilesById() {
        // Given
        when(tasksService.findByProfilesId(1)).thenReturn(new ArrayList<>());
        doNothing().when(tasksService).deleteByProfilesId(anyList());
        doNothing().when(profilesDao).deleteById(1);

        // When
        profilesService.deleteById(1);

        // Then
        verify(profilesDao, times(1)).deleteById(1);
        verify(tasksService, times(1)).deleteByProfilesId(anyList());
    }

    @Test
    void shouldHashPasswordCorrectly() {
        // Given
        String password = "password";

        // When
        String hashedPassword = profilesService.hashPassword(password);

        // Then
        assertNotNull(hashedPassword);
        assertTrue(BCrypt.checkpw(password, hashedPassword));
    }
}