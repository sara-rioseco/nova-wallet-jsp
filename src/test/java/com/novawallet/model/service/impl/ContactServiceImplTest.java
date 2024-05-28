package com.novawallet.model.service.impl;

import com.novawallet.model.dao.ContactDAO;
import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.dao.impl.ContactDAOImpl;
import com.novawallet.model.dao.impl.UserDAOImpl;
import com.novawallet.model.entity.Contact;
import com.novawallet.model.entity.User;
import com.novawallet.model.service.ContactService;
import com.novawallet.shared.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactServiceImplTest {
    @Mock
    private ContactDAO contactDAO;

    @Mock
    private DB mockDB;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private ContactServiceImpl contactService;

    Timestamp creationDate = Timestamp.from(Instant.now());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Disabled
    @Test
    void testCreateContactSuccess() throws SQLException {
        Contact contact = new Contact(1, "John", "Doe", "contact@example.com", 2, 3, creationDate);
        User owner = new User(3, "Owner", "User", "owner@example.com","password", creationDate);
        User contactUser = new User(2, "Contact", "User", "contact@example.com", "password", creationDate);
        when(mockDB.update(anyString())).thenReturn(1);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(userDAO.getUserByEmail("contact@example.com")).thenReturn(contactUser);
        when(userDAO.getUserById(2)).thenReturn(contactUser);
        when(contactDAO.addContact(contact)).thenReturn(true);

        boolean result = contactService.createContact(contact);

        assertTrue(result);
        verify(userDAO).getUserByEmail("john.doe@example.com");
        verify(userDAO).getUserById(3);
        verify(userDAO).getUserById(2);
        verify(contactDAO).addContact(contact);
    }

    @Disabled
    @Test
    void testCreateContactFailure() throws SQLException {
        Contact contact = new Contact("John", "john@example.com", 2, 3);
        when(mockDB.update(anyString())).thenReturn(1);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.next()).thenReturn(true, false);
        boolean result = contactService.createContact(contact);
        assertFalse(result);
        verify(contactDAO, never()).addContact(contact);
    }

    @Test
    void testGetContactById() {
        Contact contact = new Contact("John", "john@example.com", 2, 3);
        when(contactDAO.getContactById(1)).thenReturn(contact);
        Contact result = contactService.getContactById(1);
        assertNotNull(result);
        assertEquals(contact, result);
        verify(contactDAO).getContactById(1);
    }

    @Test
    void testGetContactUserIdByContactId() {
        when(contactDAO.getContactUserIdByContactId(1)).thenReturn(2);
        int result = contactService.getContactUserIdByContactId(1);
        assertEquals(2, result);
        verify(contactDAO).getContactUserIdByContactId(1);
    }

    @Disabled
    @Test
    void testUpdateContactSuccess() throws SQLException {
        Contact contact = new Contact("John",  "john@example.com", 2, 3);
        when(mockDB.update(anyString())).thenReturn(1);
        when(mockResultSet.next()).thenReturn(true, false);
        when(contactDAO.getContactById(1)).thenReturn(contact);
        when(contactDAO.updateContact(contact)).thenReturn(true);
        boolean result = contactService.updateContact(contact);
        assertTrue(result);
        verify(contactDAO).getContactById(1);
        verify(contactDAO).updateContact(contact);
    }

    @Test
    void testUpdateContactFailure() {
        Contact contact = new Contact("John",  "john@example.com", 2, 3);
        when(contactDAO.getContactById(0)).thenReturn(null);

        boolean result = contactService.updateContact(contact);

        assertFalse(result);
        verify(contactDAO).getContactById(0);
        verify(contactDAO, never()).updateContact(contact);
    }

    @Test
    void testDeleteContactSuccess() {
        when(contactDAO.getContactById(1)).thenReturn(new Contact("John",  "john@example.com", 2, 3));
        when(contactDAO.deleteContact(1)).thenReturn(true);

        boolean result = contactService.deleteContact(1);

        assertTrue(result);
        verify(contactDAO).getContactById(1);
        verify(contactDAO).deleteContact(1);
    }

    @Test
    void testDeleteContactFailure() {
        when(contactDAO.getContactById(1)).thenReturn(null);

        boolean result = contactService.deleteContact(1);

        assertFalse(result);
        verify(contactDAO).getContactById(1);
        verify(contactDAO, never()).deleteContact(1);
    }

    @Test
    void getAllContacts() {
        List<Contact> contacts = Arrays.asList(
                new Contact("John",  "john@example.com", 2, 3),
                new Contact("Jane",  "jane@example.com", 4, 5)
        );
        when(contactDAO.getAllContacts()).thenReturn(contacts);

        List<Contact> result = contactService.getAllContacts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(contacts, result);
        verify(contactDAO).getAllContacts();
    }

    @Test
    void getAllContactsByOwnerId() {
        List<Contact> contacts = Arrays.asList(
                new Contact("John",  "john@example.com", 2, 3),
                new Contact("Jane",  "jane@example.com", 4, 5)
        );
        when(contactDAO.getContactsByOwnerId(3)).thenReturn(contacts);

        List<Contact> result = contactService.getAllContactsByOwnerId(3);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(contacts, result);
        verify(contactDAO).getContactsByOwnerId(3);
    }
}