package com.example.agenda.service.mapper;

import com.example.agenda.controller.dto.TaskRequestDto;
import com.example.agenda.controller.dto.TaskResponseDto;
import com.example.agenda.repository.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskRequestDto toTaskRequestDto(Task task);
    TaskResponseDto toTaskResponseDto(Task task);

    @Mapping(source = "title", target = "title")
    Task toTask(TaskRequestDto dto);

}
