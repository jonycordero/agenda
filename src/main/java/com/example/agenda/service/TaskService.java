package com.example.agenda.service;

import com.example.agenda.controller.dto.TaskRequestDto;
import com.example.agenda.controller.dto.TaskResponseDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<TaskResponseDto> findAll();
    Optional<TaskResponseDto> findById(Long id);
    TaskResponseDto save(TaskRequestDto taskRequestDto);
    void delete(Long id);
    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequest);
    TaskResponseDto patchTask(Long id, TaskRequestDto taskRequest);
}
