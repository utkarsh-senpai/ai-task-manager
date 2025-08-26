package com.utkarsh.aitaskmanager.unit.repository;

import com.utkarsh.aitaskmanager.model.User;
import com.utkarsh.aitaskmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail_ReturnsUser() {
        // Arrange: Create and save a user
        User user = new User();
        user.setName("Utkarsh");
        user.setEmail("utkarsh@example.com");
        user.setPassword("password123");
        userRepository.save(user);

        // Act: Find user by email
        Optional<User> foundUserOpt = userRepository.findByEmail("utkarsh@example.com");

        // Assert: Verify user is present and fields match
        assertThat(foundUserOpt).isPresent();
        User foundUser = foundUserOpt.get();
        assertThat(foundUser.getName()).isEqualTo("Utkarsh");
        assertThat(foundUser.getEmail()).isEqualTo("utkarsh@example.com");
    }

    @Test
    public void testFindByEmail_NotFound() {
        // Act: Search for non-existent email
        Optional<User> foundUserOpt = userRepository.findByEmail("nonexistent@example.com");

        // Assert: Should be empty
        assertThat(foundUserOpt).isNotPresent();
    }
}

