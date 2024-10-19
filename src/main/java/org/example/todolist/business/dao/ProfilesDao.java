package org.example.todolist.business.dao;

import org.example.todolist.domain.Profiles;

import java.util.Optional;

public interface ProfilesDao {

    Optional<Profiles> findById(Integer profileId);

    Optional<Profiles> findByEmail(String email);

    Optional<Profiles> findByUsername(String username);

    Profiles saveProfiles(Profiles profiles);

    void deleteById(Integer profilesId);
}
