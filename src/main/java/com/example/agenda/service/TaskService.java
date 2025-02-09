package com.example.agenda.service;

import com.example.agenda.controller.dto.TaskRequestDto;
import com.example.agenda.controller.dto.TaskResponseDto;
import com.example.agenda.repository.TaskRepository;
import com.example.agenda.repository.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskResponseDto> findAll() {
        return taskRepository.findAll().stream().map(
                task -> new TaskResponseDto(task.getId(),task.getTitle(),task.getDescription(),task.isCompleted())
        ).toList();
    }

    public Optional<TaskResponseDto> findById(Long id) {
        return taskRepository.findById(id)
                .map(task -> new TaskResponseDto(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted()));
    }

    public TaskResponseDto save(TaskRequestDto taskRequestDto) {


        Task task = convertirTask(taskRequestDto);

        Task savedTask = taskRepository.save(task);

        TaskResponseDto taskResponseDto = toTaskResponseDto(savedTask);

        return taskResponseDto;
    }

    private TaskResponseDto toTaskResponseDto(Task task) {
        return new TaskResponseDto(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
    }

    private Task convertirTask(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.title());
        task.setDescription(taskRequestDto.description());
        task.setCompleted(taskRequestDto.completed());
        return task;
    }


}
