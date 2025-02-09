package com.example.agenda.service;

import com.example.agenda.controller.dto.TaskRequestDto;
import com.example.agenda.controller.dto.TaskResponseDto;
import com.example.agenda.repository.TaskRepository;
import com.example.agenda.repository.model.Task;
import com.example.agenda.service.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponseDto> findAll() {
        return taskRepository.findAll().stream().map(
                task -> new TaskResponseDto(task.getId(),task.getTitle(),task.getDescription(),task.isCompleted())
        ).toList();
    }

    @Override
    public Optional<TaskResponseDto> findById(Long id) {
        return taskRepository.findById(id)
                .map(task -> new TaskResponseDto(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted()));
    }

    @Override
    public TaskResponseDto save(TaskRequestDto taskRequestDto) {
        return taskMapper.toTaskResponseDto(taskRepository.save(taskMapper.toTask(taskRequestDto)));
    }

    @Override
    public void delete(Long id) {
        //return taskRepository.findById(id).stream().peek(task -> {
            taskRepository.deleteById(id);
        //});
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequest) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(taskRequest.title());
            task.setDescription(taskRequest.description());
            task.setCompleted(taskRequest.completed());
            return taskMapper.toTaskResponseDto(taskRepository.save(task));
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public TaskResponseDto patchTask(Long id, TaskRequestDto taskRequest) {
        return taskRepository.findById(id).map(task -> {

            if (taskRequest.title() != null) {
                task.setTitle(taskRequest.title());
            }
            if (taskRequest.description() != null) {
                task.setDescription(taskRequest.description());
            }
            if (taskRequest.completed() != null) {
                task.setCompleted(taskRequest.completed());
            }
            return taskMapper.toTaskResponseDto(taskRepository.save(task));
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }






}
