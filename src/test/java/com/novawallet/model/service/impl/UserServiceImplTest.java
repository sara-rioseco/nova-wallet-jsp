package com.novawallet.model.service.impl;

import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    Timestamp creationDate = Timestamp.from(Instant.now());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUserSuccess() {
        User user = new User("John", "Doe", "john.doe@example.com", "password123");
        when(userDAO.getUserByEmail("john.doe@example.com")).thenReturn(null);
        when(userDAO.addUser(user)).thenReturn(true);
        boolean result = userService.createUser(user);
        assertTrue(result);
        verify(userDAO).getUserByEmail("john.doe@example.com");
        verify(userDAO).addUser(user);
    }

    @Test
    void createUserFailure() {
        User user = new User("John", "Doe", "john.doe@example.com", "password123");
        when(userDAO.getUserByEmail("john.doe@example.com")).thenReturn(user);
        boolean result = userService.createUser(user);
        assertFalse(result);
        verify(userDAO).getUserByEmail("john.doe@example.com");
        verify(userDAO, never()).addUser(any(User.class));
    }

    @Test
    void getUserById() {
        User user = new User(1, "John", "Doe", "john.doe@example.com", "password123", creationDate);
        when(userDAO.getUserById(1)).thenReturn(user);
        User result = userService.getUserById(1);
        assertNotNull(result);
        assertEquals(user, result);
        verify(userDAO).getUserById(1);
    }

    @Test
    void getUserByEmail() {
        User user = new User(1, "John", "Doe", "john.doe@example.com", "password123", creationDate);
        when(userDAO.getUserByEmail("john.doe@example.com")).thenReturn(user);
        User result = userService.getUserByEmail("john.doe@example.com");
        assertNotNull(result);
        assertEquals(user, result);
        verify(userDAO).getUserByEmail("john.doe@example.com");
    }

    @Test
    void updateUserSuccess() {
        User user = new User(1, "John", "Doe", "john.doe@example.com", "password123", creationDate);
        when(userDAO.getUserById(1)).thenReturn(user);
        when(userDAO.updateUser(user)).thenReturn(true);
        boolean result = userService.updateUser(user);
        assertTrue(result);
        verify(userDAO).getUserById(1);
        verify(userDAO).updateUser(user);
    }

    @Test
    void updateUserFailure() {
        User user = new User(1, "John", "Doe", "john.doe@example.com", "password123", creationDate);
        when(userDAO.getUserById(1)).thenReturn(null);
        boolean result = userService.updateUser(user);
        assertFalse(result);
        verify(userDAO).getUserById(1);
        verify(userDAO, never()).updateUser(any(User.class));
    }

    @Test
    void deleteUserSuccess() {
        when(userDAO.getUserById(1)).thenReturn(new User(1, "John", "Doe", "john.doe@example.com", "password123", creationDate));
        when(userDAO.deleteUser(1)).thenReturn(true);
        boolean result = userService.deleteUser(1);
        assertTrue(result);
        verify(userDAO).getUserById(1);
        verify(userDAO).deleteUser(1);
    }

    @Test
    void deleteUserFailure() {
        when(userDAO.getUserById(1)).thenReturn(null);
        boolean result = userService.deleteUser(1);
        assertFalse(result);
        verify(userDAO).getUserById(1);
        verify(userDAO, never()).deleteUser(1);
    }

    @Test
    void getAllUsers() {
        List<User> users = Arrays.asList(
                new User(1, "John", "Park", "john.doe@example.com", "password123", creationDate),
                new User(2, "Jane", "Smith", "jane.doe@example.com", "password123", creationDate)
        );
        when(userDAO.getAllUsers()).thenReturn(users);
        List<User> result = userService.getAllUsers();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(users, result);
        verify(userDAO).getAllUsers();
    }
}