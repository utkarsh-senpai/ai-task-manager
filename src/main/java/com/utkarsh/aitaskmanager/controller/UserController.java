package com.utkarsh.aitaskmanager.controller;

import com.utkarsh.aitaskmanager.model.User;
import com.utkarsh.aitaskmanager.model.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.utkarsh.aitaskmanager.service.UserService;

import java.time.Instant;
import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>>getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String>addUser(@Valid @RequestBody UserDto userDto){
        if (userService.checkEmail(userDto.getEmail())){
            return new ResponseEntity<>("User already exist.",HttpStatus.BAD_REQUEST);
        }
        else {
            User user = new User();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setCreatedAt(String.valueOf(Instant.now()));
            userService.addUser(user);
            return new ResponseEntity<>("User added successfully.", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String>deleteUserWithId(@PathVariable Long id){
        if(userService.deleteUser(id)) {
            return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
