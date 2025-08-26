package com.utkarsh.aitaskmanager.unit.service;

import com.utkarsh.aitaskmanager.model.Task;
import com.utkarsh.aitaskmanager.model.User;
import com.utkarsh.aitaskmanager.repository.UserRepository;
import com.utkarsh.aitaskmanager.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsers_ReturnsUserList() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getUsers();

        assertThat(result).hasSize(2);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testAddUser_SavesUser() {
        User user = new User();
        userService.addUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser_UserExists_DeletesUserAndUnlinksTasks() {
        User user = new User();
        Task task = new Task();
        task.setUser(user);
        user.setTasks(Collections.singletonList(task));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Boolean result = userService.deleteUser(1L);

        assertThat(result).isTrue();
        assertThat(task.getUser()).isNull();

        InOrder inOrder = inOrder(userRepository);
        inOrder.verify(userRepository).save(user);
        inOrder.verify(userRepository).deleteById(1L);
    }

    @Test
    public void testDeleteUser_UserDoesNotExist_ReturnsFalse() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Boolean result = userService.deleteUser(1L);

        assertThat(result).isFalse();
        verify(userRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testCheckEmail_UserExists_ReturnsTrue() {
        when(userRepository.findByEmail("email@example.com")).thenReturn(Optional.of(new User()));

        Boolean exists = userService.checkEmail("email@example.com");

        assertThat(exists).isTrue();
    }

    @Test
    public void testCheckEmail_UserDoesNotExist_ReturnsFalse() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        Boolean exists = userService.checkEmail("notfound@example.com");

        assertThat(exists).isFalse();
    }
}

