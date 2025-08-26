package com.utkarsh.aitaskmanager.service;

import com.utkarsh.aitaskmanager.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Boolean addTask(Task t);
}
