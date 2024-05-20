package com.novawallet.model.dto;

import com.novawallet.model.entity.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static com.novawallet.shared.Utils.formatDate;
import static com.novawallet.shared.Utils.formatTime;
import static org.junit.jupiter.api.Assertions.*;

class ContactDTOTest {

    Contact contact;
    ContactDTO contactDTO;
    Timestamp creationDate = Timestamp.from(Instant.now());

    @BeforeEach
    void setUp() {
        contact = new Contact(0,"Emily", "Dickinson", "emily@test.com", 2, 1, creationDate);
        contactDTO = new ContactDTO(contact, 1);
    }

    @Test
    void getId() {
        assertEquals(0, contactDTO.getId());
    }

    @Test
    void getName() {
        assertEquals("Emily", contactDTO.getName());
    }

    @Test
    void getLast() {
        assertEquals("Dickinson", contactDTO.getLast());
    }

    @Test
    void getMail() {
        assertEquals("emily@test.com", contactDTO.getMail());
    }

    @Test
    void getCurrentUserId() {
        assertEquals(1, contactDTO.getCurrentUserId());
    }

    @Test
    void getContactUserId() {
        assertEquals(2, contactDTO.getContactUserId());
    }

    @Test
    void getOwnerUserId() {
        assertEquals(1, contactDTO.getOwnerUserId());
    }

    @Test
    void getDate() {
        assertEquals("On " + formatDate(creationDate) + " at " + formatTime(creationDate), contactDTO.getDate());
    }
}