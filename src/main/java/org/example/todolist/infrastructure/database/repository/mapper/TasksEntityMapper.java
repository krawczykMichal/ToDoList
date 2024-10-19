package org.example.todolist.infrastructure.database.repository.mapper;


import org.example.todolist.domain.Tasks;
import org.example.todolist.infrastructure.database.entity.TasksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TasksEntityMapper {

    @Mapping(target = "profiles", ignore = true)
    Tasks mapFromTasksEntity(TasksEntity tasksEntity);

    TasksEntity mapToTasksEntity(Tasks tasks);
}
