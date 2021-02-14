package com.example.demo.service;

import com.example.demo.dao.TaskRepository;
import com.example.demo.entity.dto.TaskDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Service
@Validated
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskDto saveTask(@Valid final TaskDto task) {
        return taskRepository.save(task.toDbo()).toDto();
    }
}
