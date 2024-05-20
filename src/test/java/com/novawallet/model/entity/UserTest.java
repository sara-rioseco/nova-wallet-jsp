package com.novawallet.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;
    Timestamp creationDate = Timestamp.from(Instant.now());

    @BeforeEach
    void setUp() {
        user = new User("John", "Smith", "john@test.com", "password");
    }

    @Test
    void getId() {
        assertEquals(0, user.getId());
    }

    @Test
    void setId() {
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    void getFirstName() {
        assertEquals("John", user.getFirstName());
    }

    @Test
    void setFirstName() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Smith", user.getLastName());
    }

    @Test
    void setLastName() {
        user.setLastName("Jones");
        assertEquals("Jones", user.getLastName());
    }

    @Test
    void getFullName() {
        assertEquals("John Smith", user.getFullName());
    }

    @Test
    void getEmail() {
        assertEquals("john@test.com", user.getEmail());    }

    @Test
    void setEmail() {
        user.setEmail("newemail@test.com");
        assertEquals("newemail@test.com", user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void setPassword() {
        user.setPassword("SuperSecurePassword");
        assertEquals("SuperSecurePassword", user.getPassword());
    }

    @Test
    void getCreationDate() {
        User newUser = new User(0,"John", "Smith", "john@test.com", "password",creationDate);
        assertEquals(creationDate, newUser.getCreationDate());
    }
}