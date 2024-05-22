package com.novawallet.model.dao.impl;

import com.novawallet.model.entity.Account;
import com.novawallet.model.entity.Currency;
import com.novawallet.shared.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

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

@ExtendWith(MockitoExtension.class)
class AccountDAOImplTest {

    @InjectMocks
    private AccountDAOImpl accountDAO;

    @Mock
    private DB mockDB = mock(DB.class);

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountDAO = new AccountDAOImpl(mockDB);
    }

    @Test
    void testAddAccount() {
        Account account = new Account(1,1);
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = accountDAO.addAccount(account);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }

    @Test
    void addAccountSQLExceptionTest() {
        Account account = new Account(1,1);
        when(mockDB.update(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> accountDAO.addAccount(account));
    }

    @Test
    void testGetAllAccounts() throws SQLException {
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getInt("owner_id")).thenReturn(1, 2);
        when(mockResultSet.getInt("currency_id")).thenReturn(1, 1);
        List<Account> accounts = accountDAO.getAllAccounts();
        assertEquals(2, accounts.size());
        assertEquals(1, accounts.getFirst().getId());
        assertEquals(1, accounts.getFirst().getOwnerId());
        assertEquals(1, accounts.getFirst().getCurrencyId());
        assertEquals(2, accounts.get(1).getId());
        assertEquals(2, accounts.get(1).getOwnerId());
        assertEquals(1, accounts.get(1).getCurrencyId());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(3)).next();  // 3 times: true, true, false
        verify(mockResultSet, times(2)).getInt("id");
        verify(mockResultSet, times(2)).getInt("owner_id");
        verify(mockResultSet, times(2)).getInt("currency_id");
    }

    @Test
    void getAllAccountsSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> accountDAO.getAllAccounts());
    }

    @Test
    void testGetAccountsByOwnerId() throws SQLException {
        int ownerId = 1;
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getInt("currency_id")).thenReturn(1);
        Account account = accountDAO.getAccountsByOwnerId(ownerId).getFirst();
        assertEquals(1, account.getId());
        assertEquals(1, account.getOwnerId());
        assertEquals(1, account.getCurrencyId());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getInt("id");
        verify(mockResultSet, times(1)).getInt("currency_id");
    }

    @Test
    void getAccountsByOwnerIdSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> accountDAO.getAccountsByOwnerId(anyInt()));
    }

    @Test
    void testGetAccountById() throws SQLException {
        int id = 1;
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("owner_id")).thenReturn(1);
        when(mockResultSet.getInt("currency_id")).thenReturn(1);
        Account account = accountDAO.getAccountById(id);
        assertEquals(1, account.getOwnerId());
        assertEquals(1, account.getCurrencyId());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getInt("owner_id");
        verify(mockResultSet, times(1)).getInt("currency_id");
    }

    @Test
    void getAccountByIdSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> accountDAO.getAccountById(anyInt()));
    }

    @Test
    void testUpdateAccount() {
        Account account = new Account(1,1,1,new BigDecimal(0), Timestamp.from(Instant.now()));
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = accountDAO.updateAccount(account);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }

    @Test
    void testDeleteAccount() {
        int id = 1;
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = accountDAO.deleteAccount(id);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }
}