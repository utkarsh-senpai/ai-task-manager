package com.utkarsh.aitaskmanager.service;

import com.utkarsh.aitaskmanager.model.Task;
import com.utkarsh.aitaskmanager.model.User;
import com.utkarsh.aitaskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }


    @Override
    public Boolean deleteUser(Long  id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            for (Task task :user.getTasks()){
                task.setUser(null);
            }
            userRepository.save(user);
            userRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean checkEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

}
