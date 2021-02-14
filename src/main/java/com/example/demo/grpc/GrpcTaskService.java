package com.example.demo.grpc;

import com.example.demo.entity.dto.TaskDto;
import com.example.demo.service.TaskService;
import com.example.demo.task.SaveTaskRequest;
import com.example.demo.task.Task;
import com.example.demo.task.TaskServiceGrpc;
import com.example.demo.util.GrpcUtil;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class GrpcTaskService extends TaskServiceGrpc.TaskServiceImplBase {

    private final TaskService taskService;

    @Override
    public void saveTask(final SaveTaskRequest request, final StreamObserver<Task> responseObserver) {
        final Integer taskId = request.getTaskId();
        final Task task = request.getTask();

        GrpcUtil.validateUserId(request.getUserId());

        if (!Objects.equals(taskId, task.getId())) {
            throw Status.INVALID_ARGUMENT.withDescription("Task id mismatch").asRuntimeException();
        }

        final TaskDto taskDto = TaskDto.fromGrpc(task);
        final TaskDto savedTask = taskService.saveTask(taskDto);

        responseObserver.onNext(savedTask.toGrpc());
        responseObserver.onCompleted();
    }
}
