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
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

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


        Task task = convertirTask(taskRequestDto);

        Task savedTask = taskRepository.save(task);

        TaskResponseDto taskResponseDto = toTaskResponseDto(savedTask);

        return taskResponseDto;
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequest) {

        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            Task taskToUpdate = task.get();
            taskToUpdate.setTitle(taskRequest.title());
            taskToUpdate.setDescription(taskRequest.description());
            taskToUpdate.setCompleted(taskRequest.completed());
            return toTaskResponseDto(taskRepository.save(taskToUpdate));
        }
        return null;
    }

    @Override
    public TaskResponseDto patchTask(Long id, TaskRequestDto taskRequest) {

        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            Task taskToUpdate = task.get();
            if (taskRequest.title() != null) {
                taskToUpdate.setTitle(taskRequest.title());
            }
            if (taskRequest.description() != null) {
                taskToUpdate.setDescription(taskRequest.description());
            }
            if (taskRequest.completed() != null){
                taskToUpdate.setCompleted(taskRequest.completed());
            }
            return toTaskResponseDto(taskRepository.save(taskToUpdate));
        }
        return null;
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
