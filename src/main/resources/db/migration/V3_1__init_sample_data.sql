insert into profiles (name, surname, username, email)
values ('Mariusz', 'Nowak', 'mNowak', 'Mnowak@email.com'),
       ('Adam', 'Moniak', 'aMoniak', 'Amoniak@email.com'),
       ('Monika', 'Kowal', 'mKowal', 'Mkowal@email.com'),
       ('Agnieszka', 'Kowalska', 'aKowalska', 'Akowalska@email.com'),
       ('Dariusz', 'Byk', 'dByk', 'Dbyk@email.com'),
       ('Michał', 'Piotrowski', 'mPiotrowski', 'Mpiotrowski@email.com'),
       ('Kornelia', 'Kochanowska', 'kKochanowska', 'Kkochanowska@email.com'),
       ('Jan', 'Michalski', 'jMichalski', 'Jmichalski@email.com'),
       ('Sylwia', 'Perłowska', 'sPerłowska', 'Sperłowska@email.com'),
       ('ADMIN', 'ADMIN', 'ADMIN', 'admin@email.com');

insert into tasks (profiles_id, title, description, reminder_datetime, is_completed, completed_at)
values (1, 'Task 1', 'Complete the project documentation.', '2024-08-20 09:00:00', false, null),
       (1, 'Task 2', 'Prepare a presentation for the team meeting.', '2024-08-21 11:00:00', true,
        '2024-08-21 10:30:00'),
       (2, 'Task 3', 'Review code submissions.', '2024-08-22 14:00:00', false, null),
       (2, 'Task 4', 'Fix bugs reported by QA.', '2024-08-22 16:00:00', false, null),
       (3, 'Task 5', 'Plan the next sprint.', '2024-08-23 10:00:00', true, '2024-08-22 15:00:00'),
       (3, 'Task 6', 'Update project roadmap.', '2024-08-24 13:00:00', false, null),
       (4, 'Task 7', 'Write unit tests for the new module.', '2024-08-25 09:30:00', false, null),
       (4, 'Task 8', 'Refactor legacy code.', '2024-08-26 15:30:00', false, null),
       (5, 'Task 9', 'Conduct user interviews.', '2024-08-27 11:00:00', true, '2024-08-26 10:00:00'),
       (5, 'Task 10', 'Analyze feedback from customers.', '2024-08-28 12:00:00', false, null),
       (1, 'Task 11', 'Organize team-building event.', '2024-08-29 14:00:00', false, null),
       (2, 'Task 12', 'Prepare monthly report.', '2024-08-30 09:00:00', true, '2024-08-29 16:00:00'),
       (3, 'Task 13', 'Set up CI/CD pipeline.', '2024-08-31 10:00:00', false, null),
       (4, 'Task 14', 'Update API documentation.', '2024-09-01 13:00:00', false, null),
       (5, 'Task 15', 'Research new technologies.', '2024-09-02 11:00:00', true, '2024-09-01 14:30:00'),
       (1, 'Task 16', 'Review team performance.', '2024-09-03 15:00:00', false, null),
       (2, 'Task 17', 'Prepare for client meeting.', '2024-09-04 10:00:00', false, null),
       (3, 'Task 18', 'Design database schema.', '2024-09-05 09:00:00', true, '2024-09-04 12:00:00');

insert into todo_list_app_user (username, email, password, active)
values ('mNowak', 'Mnowak@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('aMoniak', 'Amoniak@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('mKowal', 'Mkowal@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('aKowalska', 'Akowalska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('dByk', 'Dbyk@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('mPiotrowski', 'Mpiotrowski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('kKochanowska', 'Kkochanowska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('jMichalski', 'Jmichalski@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', true),
       ('sPerłowska', 'Sperłowska@email.com', '$2a$12$J19FFc1YDRkwo6.bZqbSO.eGZ1bcx5vg0nBCG5L4/Vt3obgBB.c12', false),
       ('ADMIN', 'admin@email.com', '$2a$12$YOhmtOAvoUEoDIWJal6mieKE/WU7/4Jgo.n3VR9HFFwJtnaLMt3da', true);

insert into todo_list_app_role (role)
values ('USER'),
       ('ADMIN');


insert into todo_list_app_user_role (user_id, role_id)
values (1,1),
 (2,1),
 (3,1),
 (4,1),
 (5,1),
 (6,1),
 (7,1),
 (8,1),
 (9,1),
 (10,2);