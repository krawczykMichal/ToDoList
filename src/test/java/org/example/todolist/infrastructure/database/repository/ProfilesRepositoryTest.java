package org.example.todolist.infrastructure.database.repository;

import org.example.todolist.domain.Profiles;
import org.example.todolist.infrastructure.database.entity.ProfilesEntity;
import org.example.todolist.infrastructure.database.repository.jpa.ProfilesJpaRepository;
import org.example.todolist.infrastructure.database.repository.mapper.ProfilesEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfilesRepositoryTest {

    @Mock
    private ProfilesJpaRepository profilesJpaRepository;

    @Mock
    private ProfilesEntityMapper profilesEntityMapper;

    @InjectMocks
    private ProfilesRepository profilesRepository;

    private Profiles profiles;
    private ProfilesEntity profilesEntity;

    @BeforeEach
    void setUp() {
        profiles = Profiles.builder()
                .profilesId(1)
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .username("john_doe").build();
        profilesEntity = ProfilesEntity.builder().profilesId(1)
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .username("john_doe").build();
    }

    @Test
    void shouldFindById() {
        // Given
        when(profilesJpaRepository.findById(1)).thenReturn(Optional.of(profilesEntity));
        when(profilesEntityMapper.mapFromProfilesEntity(profilesEntity)).thenReturn(profiles);

        // When
        Optional<Profiles> foundProfile = profilesRepository.findById(1);

        // Then
        assertTrue(foundProfile.isPresent());
        assertEquals("John", foundProfile.get().getName());
        verify(profilesJpaRepository, times(1)).findById(1);
        verify(profilesEntityMapper, times(1)).mapFromProfilesEntity(profilesEntity);
    }

    @Test
    void shouldFindByEmail() {
        // Given
        when(profilesJpaRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(profilesEntity));
        when(profilesEntityMapper.mapFromProfilesEntity(profilesEntity)).thenReturn(profiles);

        // When
        Optional<Profiles> foundProfile = profilesRepository.findByEmail("john.doe@example.com");

        // Then
        assertTrue(foundProfile.isPresent());
        assertEquals("john.doe@example.com", foundProfile.get().getEmail());
        verify(profilesJpaRepository, times(1)).findByEmail("john.doe@example.com");
        verify(profilesEntityMapper, times(1)).mapFromProfilesEntity(profilesEntity);
    }

    @Test
    void shouldFindByUsername() {
        // Given
        when(profilesJpaRepository.findByUsername("john_doe")).thenReturn(Optional.of(profilesEntity));
        when(profilesEntityMapper.mapFromProfilesEntity(profilesEntity)).thenReturn(profiles);

        // When
        Optional<Profiles> foundProfile = profilesRepository.findByUsername("john_doe");

        // Then
        assertTrue(foundProfile.isPresent());
        assertEquals("john_doe", foundProfile.get().getUsername());
        verify(profilesJpaRepository, times(1)).findByUsername("john_doe");
        verify(profilesEntityMapper, times(1)).mapFromProfilesEntity(profilesEntity);
    }

    @Test
    void shouldSaveProfiles() {
        // Given
        when(profilesEntityMapper.mapToProfilesEntity(profiles)).thenReturn(profilesEntity);
        when(profilesJpaRepository.save(profilesEntity)).thenReturn(profilesEntity);
        when(profilesEntityMapper.mapFromProfilesEntity(profilesEntity)).thenReturn(profiles);

        // When
        Profiles savedProfile = profilesRepository.saveProfiles(profiles);

        // Then
        assertNotNull(savedProfile);
        assertEquals("John", savedProfile.getName());
        verify(profilesJpaRepository, times(1)).save(profilesEntity);
        verify(profilesEntityMapper, times(1)).mapToProfilesEntity(profiles);
        verify(profilesEntityMapper, times(1)).mapFromProfilesEntity(profilesEntity);
    }

    @Test
    void shouldDeleteById() {
        // Given
        doNothing().when(profilesJpaRepository).deleteById(1);

        // When
        profilesRepository.deleteById(1);

        // Then
        verify(profilesJpaRepository, times(1)).deleteById(1);
    }
}