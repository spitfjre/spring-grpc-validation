package com.example.demo.controller;

import com.example.demo.entity.dto.TaskDto;
import com.example.demo.service.TaskService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("task")
@RequiredArgsConstructor
@RestController
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/{id}")
    @PreAuthorize("#userId == authentication.principal.userid")
    public TaskDto saveTask(
        @PathVariable("id") final Integer taskId,
        @RequestBody final TaskDto task,
        @RequestParam final String userId,
        final Authentication authentication
    ) {
        if (!Objects.equals(userId, task.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User id mismatch");
        }

        if (!Objects.equals(taskId, task.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task id mismatch");
        }

        return taskService.saveTask(task);
    }
}
