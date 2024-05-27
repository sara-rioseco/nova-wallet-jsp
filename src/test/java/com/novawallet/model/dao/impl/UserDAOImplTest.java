package com.novawallet.model.dao.impl;

import com.novawallet.model.entity.User;
import com.novawallet.shared.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserDAOImplTest {

    @InjectMocks
    private UserDAOImpl userDAO;

    @Mock
    private DB mockDB = mock(DB.class);

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDAO = new UserDAOImpl(mockDB);
    }

    @Test
    void addUser() {
        User user = new User("John", "Smith", "john@test.com", "password");
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = userDAO.addUser(user);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }

    @Test
    void addUserSQLExceptionTest() {
        User user = new User("John", "Smith", "john@test.com", "password");
        when(mockDB.update(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> userDAO.addUser(user));
    }

    @Test
    void getAllUsers() throws SQLException {
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getString("first_name")).thenReturn("John", "Mary");
        when(mockResultSet.getString("email")).thenReturn("john@test.com", "mary@test.com");
        List<User> users = userDAO.getAllUsers();
        assertEquals(2, users.size());
        assertEquals(1, users.getFirst().getId());
        assertEquals("John", users.getFirst().getFirstName());
        assertEquals("john@test.com", users.getFirst().getEmail());
        assertEquals(2, users.get(1).getId());
        assertEquals("Mary", users.get(1).getFirstName());
        assertEquals("mary@test.com", users.get(1).getEmail());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(3)).next();
        verify(mockResultSet, times(2)).getInt("id");
        verify(mockResultSet, times(2)).getString("first_name");
        verify(mockResultSet, times(2)).getString("email");
    }

    @Test
    void getAllUsersSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> userDAO.getAllUsers());
    }

    @Test
    void getUserByEmail() throws SQLException {
        String email = "john@test.com";
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("first_name")).thenReturn("John");
        User user = userDAO.getUserByEmail(email);
        assertEquals(1, user.getId());
        assertEquals("John", user.getFirstName());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getInt("id");
        verify(mockResultSet, times(1)).getString("first_name");
    }


    @Test
    void getUserByEmailSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> userDAO.getUserByEmail(anyString()));
    }

    @Test
    void getUserById() throws SQLException {
        int id = 1;
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("email")).thenReturn("john@test.com");
        when(mockResultSet.getString("first_name")).thenReturn("John");
        User user = userDAO.getUserById(id);
        assertEquals("john@test.com", user.getEmail());
        assertEquals("John", user.getFirstName());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getString("email");
        verify(mockResultSet, times(1)).getString("first_name");
    }

    @Test
    void getUserByIdSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> userDAO.getUserById(anyInt()));
    }


    @Test
    void updateUser() {
        User user = new User("John", "Smith", "john@test.com", "password");
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = userDAO.updateUser(user);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }

    @Test
    void deleteUser() {
        int id = 1;
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = userDAO.deleteUser(id);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }
}