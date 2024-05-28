package com.novawallet.model.service.impl;

import com.novawallet.model.dao.AccountDAO;
import com.novawallet.model.dao.CurrencyDAO;
import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.entity.Account;
import com.novawallet.model.entity.Currency;
import com.novawallet.model.entity.TransactionType;
import com.novawallet.model.entity.User;
import com.novawallet.shared.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private AccountDAO accountDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private CurrencyDAO currencyDAO;

    @Mock
    private DB mockDB;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private ResultSet mockResultSet;

    Timestamp creationDate = Timestamp.from(Instant.now());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Disabled
    @Test
    void testCreateAccountSuccess() throws SQLException {
        Account account = new Account(1, 2, 3, BigDecimal.ZERO, creationDate);
        User user = new User("John", "Smith", "john@mail.com", "password");
        Currency currency = new Currency(1,"US Dollars", "USD");
        when(userDAO.getUserById(2)).thenReturn(user);
        when(currencyDAO.getCurrencyById(3)).thenReturn(currency);
        when(accountDAO.addAccount(account)).thenReturn(true);

        boolean result = accountService.createAccount(account);

        assertTrue(result);
        verify(userDAO).getUserById(2);
        verify(currencyDAO).getCurrencyById(3);
        verify(accountDAO).addAccount(account);
    }

    @Disabled
    @Test
    void testCreateAccountFailure() throws SQLException {
        Account account = new Account(1, 2, 3, BigDecimal.ZERO, creationDate);
        when(userDAO.getUserById(2)).thenReturn(null);

        boolean result = accountService.createAccount(account);

        assertFalse(result);
        verify(userDAO).getUserById(2);
        verify(currencyDAO, never()).getCurrencyById(anyInt());
        verify(accountDAO, never()).addAccount(account);
    }

    @Test
    void testGetAccountById() {
        Account account = new Account(1, 2, 3, BigDecimal.ZERO, creationDate);
        when(accountDAO.getAccountById(1)).thenReturn(account);

        Account result = accountService.getAccountById(1);

        assertNotNull(result);
        assertEquals(account, result);
        verify(accountDAO).getAccountById(1);
    }

    @Test
    void testUpdateBalanceSuccessDeposit() {
        Account account = new Account(1, 2, 3, BigDecimal.ZERO, creationDate);
        BigDecimal amount = new BigDecimal("100.00");
        when(accountDAO.getAccountById(1)).thenReturn(account);
        when(accountDAO.updateAccount(any(Account.class))).thenReturn(true);

        boolean result = accountService.updateBalance(1, amount, TransactionType.deposit, false);

        assertTrue(result);
        assertEquals(new BigDecimal("100.00"), account.getBalance());
        verify(accountDAO).getAccountById(1);
        verify(accountDAO).updateAccount(account);
    }

    @Test
    void testUpdateBalanceSuccessWithdrawal() {
        Account account = new Account(1, 2, 3, new BigDecimal("200.00"), creationDate);
        BigDecimal amount = new BigDecimal("100.00");
        when(accountDAO.getAccountById(1)).thenReturn(account);
        when(accountDAO.updateAccount(any(Account.class))).thenReturn(true);

        boolean result = accountService.updateBalance(1, amount, TransactionType.withdrawal, true);

        assertTrue(result);
        assertEquals(new BigDecimal("100.00"), account.getBalance());
        verify(accountDAO).getAccountById(1);
        verify(accountDAO).updateAccount(account);
    }

    @Test
    void testUpdateBalanceFailure() {
        BigDecimal amount = new BigDecimal("100.00");
        when(accountDAO.getAccountById(1)).thenReturn(null);

        boolean result = accountService.updateBalance(1, amount, TransactionType.deposit, false);

        assertFalse(result);
        verify(accountDAO).getAccountById(1);
        verify(accountDAO, never()).updateAccount(any(Account.class));
    }

    @Test
    void testDeleteAccountSuccess() {
        when(accountDAO.getAccountById(1)).thenReturn(new Account(1, 2, 3, BigDecimal.ZERO, creationDate));
        when(accountDAO.deleteAccount(1)).thenReturn(true);

        boolean result = accountService.deleteAccount(1);

        assertTrue(result);
        verify(accountDAO).getAccountById(1);
        verify(accountDAO).deleteAccount(1);
    }

    @Test
    void testDeleteAccountFailure() {
        when(accountDAO.getAccountById(1)).thenReturn(null);

        boolean result = accountService.deleteAccount(1);

        assertFalse(result);
        verify(accountDAO).getAccountById(1);
        verify(accountDAO, never()).deleteAccount(1);
    }

    @Test
    void testGetAccountsByOwnerId() {
        List<Account> accounts = Arrays.asList(
                new Account(1, 2, 3, BigDecimal.ZERO, creationDate),
                new Account(2, 2, 3, new BigDecimal("100.00"), creationDate)
        );
        when(accountDAO.getAccountsByOwnerId(2)).thenReturn(accounts);

        List<Account> result = accountService.getAccountsByOwnerId(2);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(accounts, result);
        verify(accountDAO).getAccountsByOwnerId(2);
    }

    @Test
    void testGetAllAccounts() {
        List<Account> accounts = Arrays.asList(
                new Account(1, 2, 3, BigDecimal.ZERO, creationDate),
                new Account(2, 2, 3, new BigDecimal("100.00"), creationDate)
        );
        when(accountDAO.getAllAccounts()).thenReturn(accounts);

        List<Account> result = accountService.getAllAccounts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(accounts, result);
        verify(accountDAO).getAllAccounts();
    }
}