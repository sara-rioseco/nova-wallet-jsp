package com.novawallet.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    Contact contact;
    Timestamp creationDate = Timestamp.from(Instant.now());

    @BeforeEach
    void setUp() {
        contact = new Contact("Emily", "emily@test.com", 2, 1);
    }

    @Test
    void getId() {
        assertEquals(0, contact.getId());
    }

    @Test
    void setId() {
        contact.setId(2);
        assertEquals(2, contact.getId());
    }

    @Test
    void getFirstName() {
        assertEquals("Emily", contact.getFirstName());
    }

    @Test
    void setFirstName() {
        contact.setFirstName("Martha");
        assertEquals("Martha", contact.getFirstName());
    }

    @Test
    void getLastName() {
        assertNull(contact.getLastName());
    }

    @Test
    void setLastName() {
        contact.setLastName("Dickinson");
        assertEquals("Dickinson", contact.getLastName());
    }

    @Test
    void getFullName() {
        assertEquals("Emily", contact.getFullName());
        contact.setLastName("Dickinson");
        assertEquals("Emily Dickinson", contact.getFullName());
    }

    @Test
    void getEmail() {
        assertEquals("emily@test.com", contact.getEmail());
    }

    @Test
    void setEmail() {
        contact.setEmail("newEmail@test.com");
        assertEquals("newEmail@test.com", contact.getEmail());
    }

    @Test
    void getContactUserId() {
        assertEquals(2, contact.getContactUserId());
    }

    @Test
    void getOwnerUserId() {
        assertEquals(1, contact.getOwnerUserId());
    }

    @Test
    void getCreationDate() {
        Contact newContact = new Contact(0, "Emily", "Dickinson", "emily@test.com", 2, 1, creationDate);
        assertEquals(creationDate, newContact.getCreationDate());
    }
}