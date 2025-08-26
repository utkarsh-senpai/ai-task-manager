package com.utkarsh.aitaskmanager.model.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class UserDto {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;


    public UserDto(){

    }
    public UserDto(String name, String password, String email){
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
