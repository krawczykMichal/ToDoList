package org.example.todolist.infrastructure.database.repository;


import lombok.AllArgsConstructor;
import org.example.todolist.business.dao.ProfilesDao;
import org.example.todolist.domain.Profiles;
import org.example.todolist.infrastructure.database.entity.ProfilesEntity;
import org.example.todolist.infrastructure.database.repository.jpa.ProfilesJpaRepository;
import org.example.todolist.infrastructure.database.repository.mapper.ProfilesEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProfilesRepository implements ProfilesDao {

    private final ProfilesJpaRepository profilesJpaRepository;

    private final ProfilesEntityMapper profilesEntityMapper;

    @Override
    public Optional<Profiles> findById(Integer profileId) {
        return profilesJpaRepository.findById(profileId).map(profilesEntityMapper::mapFromProfilesEntity);
    }

    @Override
    public Optional<Profiles> findByEmail(String email) {
        return profilesJpaRepository.findByEmail(email).map(profilesEntityMapper::mapFromProfilesEntity);
    }

    @Override
    public Optional<Profiles> findByUsername(String username) {
        return profilesJpaRepository.findByUsername(username).map(profilesEntityMapper::mapFromProfilesEntity);
    }

    @Override
    public Profiles saveProfiles(Profiles profiles) {
        ProfilesEntity toSave = profilesEntityMapper.mapToProfilesEntity(profiles);
        ProfilesEntity saved = profilesJpaRepository.save(toSave);
        return profilesEntityMapper.mapFromProfilesEntity(saved);
    }

    @Override
    public void deleteById(Integer profilesId) {
        profilesJpaRepository.deleteById(profilesId);
    }
}
