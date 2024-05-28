package com.novawallet.model.service.impl;

import com.novawallet.model.dao.AccountDAO;
import com.novawallet.model.dao.CurrencyDAO;
import com.novawallet.model.dao.TransactionDAO;
import com.novawallet.model.dao.UserDAO;
import com.novawallet.model.entity.*;
import com.novawallet.shared.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {

    @Mock
    private TransactionDAO transactionDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private AccountDAO accountDAO;

    @Mock
    private CurrencyDAO currencyDAO;

    @Mock
    private DB db;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    Timestamp creationDate = Timestamp.from(Instant.now());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Disabled
    @Test
    void testCreateTransactionSuccess() {
        Transaction transaction = new Transaction(1, new BigDecimal(100), 1,TransactionType.deposit, 1, 2, 2, 1, creationDate);

        when(currencyDAO.getCurrencyById(transaction.getCurrencyId())).thenReturn(mock(Currency.class));
        when(userDAO.getUserById(transaction.getSenderUserId())).thenReturn(mock(User.class));
        when(accountDAO.getAccountById(transaction.getSenderAccountId())).thenReturn(mock(Account.class));
        when(userDAO.getUserById(transaction.getReceiverUserId())).thenReturn(mock(User.class));
        when(accountDAO.getAccountById(transaction.getReceiverAccountId())).thenReturn(mock(Account.class));
        when(transactionDAO.addTransaction(transaction)).thenReturn(true);
        boolean result = transactionService.createTransaction(transaction);
        assertTrue(result);
        verify(currencyDAO).getCurrencyById(transaction.getCurrencyId());
        verify(userDAO).getUserById(transaction.getSenderUserId());
        verify(accountDAO).getAccountById(transaction.getSenderAccountId());
        verify(userDAO).getUserById(transaction.getReceiverUserId());
        verify(accountDAO).getAccountById(transaction.getReceiverAccountId());
        verify(transactionDAO).addTransaction(transaction);
    }

    @Disabled
    @Test
    void testCreateTransactionFailure() {
        Transaction transaction = new Transaction(1, new BigDecimal(100), 1,TransactionType.deposit, 1, 2, 2, 1, creationDate);
        when(currencyDAO.getCurrencyById(transaction.getCurrencyId())).thenReturn(null); // Mock missing currency
        boolean result = transactionService.createTransaction(transaction);
        assertFalse(result);
        verify(currencyDAO).getCurrencyById(transaction.getCurrencyId());
        verify(userDAO, never()).getUserById(anyInt());
        verify(accountDAO, never()).getAccountById(anyInt());
        verify(transactionDAO, never()).addTransaction(any(Transaction.class));
    }

    @Test
    void getTransactionById() {
        Transaction transaction = new Transaction(1, new BigDecimal(100), 1,TransactionType.deposit, 1, 2, 2, 1, creationDate);
        when(transactionDAO.getTransactionById(1)).thenReturn(transaction);

        Transaction result = transactionService.getTransactionById(1);

        assertNotNull(result);
        assertEquals(transaction, result);
        verify(transactionDAO).getTransactionById(1);
    }

    @Test
    void getAllTransactions() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1, BigDecimal.valueOf(100), 1,TransactionType.deposit, 1, 2, 2, 1, creationDate),
                new Transaction(2, BigDecimal.valueOf(200), 1, TransactionType.withdrawal,  1, 2, 2, 1, creationDate)
        );
        when(transactionDAO.getAllTransactions()).thenReturn(transactions);
        List<Transaction> result = transactionService.getAllTransactions();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(transactions, result);
        verify(transactionDAO).getAllTransactions();
    }

    @Test
    void getTransactionsByUserId() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1, BigDecimal.valueOf(100), 1,TransactionType.deposit, 1, 2, 2, 1, creationDate),
                new Transaction(2, BigDecimal.valueOf(200), 1, TransactionType.withdrawal,  1, 2, 2, 1, creationDate)
        );
        when(transactionDAO.getTransactionsByUserId(1)).thenReturn(transactions);
        List<Transaction> result = transactionService.getTransactionsByUserId(1);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(transactions, result);
        verify(transactionDAO).getTransactionsByUserId(1);
    }
}