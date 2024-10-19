package org.example.todolist.business;

import lombok.AllArgsConstructor;
import org.example.todolist.api.dto.ProfilesDTO;
import org.example.todolist.api.dto.mapper.ProfilesDTOMapper;
import org.example.todolist.business.dao.ProfilesDao;
import org.example.todolist.domain.Profiles;
import org.example.todolist.domain.Role;
import org.example.todolist.domain.Tasks;
import org.example.todolist.domain.User;
import org.example.todolist.domain.exception.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfilesService {

    private final ProfilesDao profilesDao;

    private final TasksService tasksService;

    private final ProfilesDTOMapper profilesDTOMapper;

    public Profiles findProfilesById(Integer profileId) {
        Optional<Profiles> profile = profilesDao.findById(profileId);
        if (profile.isEmpty()) {
            throw new NotFoundException("Could not find profile with id: " + profileId);
        }

        return profile.get();
    }

    public Profiles findProfilesByEmail(String email) {
        Optional<Profiles> profile = profilesDao.findByEmail(email);
        if (profile.isEmpty()) {
            throw new NotFoundException("Could not find profile with email: " + email);
        }
        return profile.get();
    }

    public Profiles findProfilesByUsername(String username) {
        Optional<Profiles> profile = profilesDao.findByUsername(username);
        if (profile.isEmpty()) {
            throw new NotFoundException("Could not find profile with username: " + username);
        }
        return profile.get();
    }

    @Transactional
    public void saveProfiles(ProfilesDTO profilesDTO) {
        Profiles profiles = registerProfiles(profilesDTO);
        profilesDao.saveProfiles(profiles);
    }

    private Profiles registerProfiles(ProfilesDTO profilesDTO) {
        return Profiles.builder()
                .name(profilesDTO.getName())
                .surname(profilesDTO.getSurname())
                .email(profilesDTO.getEmail())
                .username(profilesDTO.getUsername())
                .user(User.builder()
                        .username(profilesDTO.getUsername())
                        .email(profilesDTO.getEmail())
                        .password(hashPassword(profilesDTO.getProfilesUserPassword()))
                        .active(true)
                        .role(Role.builder()
                                .role("USER")
                                .build())
                        .build())
                .build();
    }

    private String hashPassword(String profilesUserPassword) {
        return BCrypt.hashpw(profilesUserPassword, BCrypt.gensalt(12));
    }

    @Transactional
    public void updateProfiles(Integer profilesId, ProfilesDTO profilesDTO) {
        Profiles profilesToUpdate = findProfilesById(profilesId);

        Profiles updatedProfiles = profilesToUpdate.withEmail(profilesDTO.getEmail())
                .withName(profilesDTO.getName())
                .withSurname(profilesDTO.getSurname())
                .withUsername(profilesToUpdate.getUsername());

        profilesDao.saveProfiles(updatedProfiles);
    }

    @Transactional
    public void deleteById(Integer profilesId) {
        List<Tasks> tasksByProfilesId = tasksService.findByProfilesId(profilesId);

        tasksService.deleteByProfilesId(tasksByProfilesId);
        profilesDao.deleteById(profilesId);
    }

    public ProfilesDTO mapProfile(Profiles profilesByUsername) {
        return profilesDTOMapper.map(profilesByUsername);
    }
}
