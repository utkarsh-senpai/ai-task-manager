package com.utkarsh.aitaskmanager.service;

import com.utkarsh.aitaskmanager.model.Task;
import com.utkarsh.aitaskmanager.repository.TaskRepository;
import com.utkarsh.aitaskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    TaskRepository taskRepository;
    UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository,UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }
//    public TaskServiceImpl(UserRepository userRepository){
//        this.userRepository =userRepository;
//    }
    @Override
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    @Override
    public Boolean addTask(Task task){
        if (userRepository.findById(task.getUser().getId()).isPresent()) {
            taskRepository.save(task);
            return true;
        }
        else{
            return false;
        }
    }
}
