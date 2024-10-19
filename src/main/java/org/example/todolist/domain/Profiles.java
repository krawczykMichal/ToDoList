package org.example.todolist.domain;


import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "profilesId")
@ToString(of = {"profilesId", "name", "surname"})
public class Profiles {

    Integer profilesId;
    String name;
    String surname;
    String email;
    String username;
    Set<Tasks> tasks;
    User user;
    Role role;
}
