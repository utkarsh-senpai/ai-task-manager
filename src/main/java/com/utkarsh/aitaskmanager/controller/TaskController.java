package com.utkarsh.aitaskmanager.controller;

import com.utkarsh.aitaskmanager.model.Task;
import com.utkarsh.aitaskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @RequestMapping(value = "/tasks",method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(),HttpStatus.OK);
    }
    @RequestMapping(value = "/tasks",method = RequestMethod.POST)
    public ResponseEntity<String> getTasks(@RequestBody Task task){
        if (taskService.addTask(task)) {
            return new ResponseEntity<>("Task added successfully.", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
