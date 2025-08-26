package com.utkarsh.aitaskmanager.repository;

import com.utkarsh.aitaskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {

}
