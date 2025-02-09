package com.example.agenda.controller;

import com.example.agenda.controller.dto.TaskRequestDto;
import com.example.agenda.controller.dto.TaskResponseDto;
import com.example.agenda.repository.model.Task;
import com.example.agenda.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskResponseDto> getAllTasks() {
        return taskService.findAll();
    }

    @PostMapping
    public TaskResponseDto createTask(@RequestBody TaskRequestDto task) {
        return taskService.save(task);
    }


}
