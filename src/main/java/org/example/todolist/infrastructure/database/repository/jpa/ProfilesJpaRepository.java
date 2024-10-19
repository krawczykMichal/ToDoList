package org.example.todolist.infrastructure.database.repository.jpa;

import aj.org.objectweb.asm.commons.Remapper;
import org.example.todolist.infrastructure.database.entity.ProfilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilesJpaRepository extends JpaRepository<ProfilesEntity, Integer> {


    Optional<ProfilesEntity> findByEmail(String email);

    Optional<ProfilesEntity> findByUsername(String username);
}
