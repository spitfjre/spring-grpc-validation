package com.example.demo.entity.dto;

import com.example.demo.entity.dbo.TaskDbo;
import com.example.demo.task.Task;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Valid
public class TaskDto {

    @NotBlank
    private String name;

    @NotNull
    private Integer id;

    @NotBlank
    private String userId;

    @NotBlank
    private String email;

    public TaskDbo toDbo() {
        return TaskDbo.builder().email(email).id(id).name(name).userId(userId).build();
    }

    public Task toGrpc() {
        return Task.newBuilder().setEmail(email).setId(id).setName(name).setUserId(userId).build();
    }

    public static TaskDto fromGrpc(final Task task) {
        return TaskDto
            .builder()
            .email(task.getEmail())
            .id(task.getId())
            .name(task.getName())
            .userId(task.getUserId())
            .build();
    }
}
