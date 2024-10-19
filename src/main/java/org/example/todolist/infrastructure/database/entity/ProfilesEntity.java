package org.example.todolist.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.util.Lazy;
import org.example.todolist.infrastructure.security.UserEntity;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "profilesId")
@ToString(of = {"profilesId", "name", "surname"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class ProfilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profiles_id")
    private Integer profilesId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profiles")
    private Set<TasksEntity> tasks;
}
