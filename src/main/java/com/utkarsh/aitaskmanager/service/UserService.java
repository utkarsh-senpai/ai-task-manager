package com.utkarsh.aitaskmanager.service;

import com.utkarsh.aitaskmanager.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addUser(User user);
    Boolean deleteUser(Long id);
    Boolean checkEmail(String email);

}
