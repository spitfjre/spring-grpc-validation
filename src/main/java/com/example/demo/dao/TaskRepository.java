package com.example.demo.dao;

import com.example.demo.entity.dbo.TaskDbo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<TaskDbo, Integer> {}
