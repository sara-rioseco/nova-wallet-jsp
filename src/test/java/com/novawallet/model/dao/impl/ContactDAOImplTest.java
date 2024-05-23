package com.novawallet.model.dao.impl;

import com.novawallet.model.entity.Account;
import com.novawallet.model.entity.Contact;
import com.novawallet.shared.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ContactDAOImplTest {

    @InjectMocks
    private ContactDAOImpl contactDAO;

    @Mock
    private DB mockDB = mock(DB.class);

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contactDAO = new ContactDAOImpl(mockDB);
    }

    @Test
    void addContact() {
        Contact contact = new Contact("Johny","john@test.com",2,1);
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = contactDAO.addContact(contact);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }

    @Test
    void addContactSQLExceptionTest() {
        Contact contact = new Contact("Johny","john@test.com",2,1);
        when(mockDB.update(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> contactDAO.addContact(contact));
    }

    @Test
    void getAllContacts() throws SQLException {
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getString("first_name")).thenReturn("John", "Mary");
        when(mockResultSet.getString("email")).thenReturn("john@test.com", "mary@test.com");
        List<Contact> contacts = contactDAO.getAllContacts();
        assertEquals(2, contacts.size());
        assertEquals(1, contacts.getFirst().getId());
        assertEquals("John", contacts.getFirst().getFirstName());
        assertEquals("john@test.com", contacts.getFirst().getEmail());
        assertEquals(2, contacts.get(1).getId());
        assertEquals("Mary", contacts.get(1).getFirstName());
        assertEquals("mary@test.com", contacts.get(1).getEmail());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(3)).next();  // 3 times: true, true, false
        verify(mockResultSet, times(2)).getInt("id");
        verify(mockResultSet, times(2)).getString("first_name");
        verify(mockResultSet, times(2)).getString("email");
    }

    @Test
    void getAllContactsSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> contactDAO.getAllContacts());
    }

    @Test
    void getContactsByOwnerId() throws SQLException {
        int ownerId = 1;
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(2);
        when(mockResultSet.getInt("contact_user_id")).thenReturn(1);
        Contact contact = contactDAO.getContactsByOwnerId(ownerId).getFirst();
        assertEquals(2, contact.getId());
        assertEquals(1, contact.getOwnerUserId());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getInt("id");
        verify(mockResultSet, times(1)).getInt("contact_user_id");
    }

    @Test
    void getContactsByOwnerIdSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> contactDAO.getContactsByOwnerId(anyInt()));
    }

    @Test
    void getContactUserIdByContactId() throws SQLException {
        int id = 1;
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("contact_user_id")).thenReturn(2);
        when(mockResultSet.getInt("owner_user_id")).thenReturn(1);
        int contactUserId = contactDAO.getContactUserIdByContactId(id);
        assertEquals(2, contactUserId);
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getInt("owner_user_id");
        verify(mockResultSet, times(1)).getInt("contact_user_id");
    }

    @Test
    void getContactById() throws SQLException {
        int id = 1;
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("owner_user_id")).thenReturn(1);
        when(mockResultSet.getInt("contact_user_id")).thenReturn(2);
        Contact contact = contactDAO.getContactById(id);
        assertEquals(1, contact.getId());
        assertEquals(2, contact.getContactUserId());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getInt("owner_user_id");
        verify(mockResultSet, times(1)).getInt("contact_user_id");
    }

    @Test
    void getAccountByIdSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> contactDAO.getContactById(anyInt()));
    }

    @Test
    void updateContact() {
        Contact contact = new Contact("Johny","john@test.com",2,1);
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = contactDAO.updateContact(contact);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }

    @Test
    void deleteContact() {
        int id = 1;
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = contactDAO.deleteContact(id);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }
}