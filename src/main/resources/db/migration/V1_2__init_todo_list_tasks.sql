create table tasks
(
    tasks_id          serial        not null,
    profiles_id       int           not null,
    title             varchar(32)   not null,
    description       varchar(1024) null,
    reminder_datetime timestamp     null,
    is_completed      boolean default false,
    completed_at      timestamp     null,
    primary key (tasks_id),
    constraint fk_todo_list_tasks_profiles
        foreign key (profiles_id)
            references profiles (profiles_id)
);