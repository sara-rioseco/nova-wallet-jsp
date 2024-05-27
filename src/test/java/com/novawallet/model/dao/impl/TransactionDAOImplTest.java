package com.novawallet.model.dao.impl;

import com.novawallet.model.entity.Contact;
import com.novawallet.model.entity.Transaction;
import com.novawallet.model.entity.TransactionType;
import com.novawallet.model.entity.User;
import com.novawallet.shared.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.novawallet.model.entity.TransactionType.deposit;
import static com.novawallet.model.entity.TransactionType.transfer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class TransactionDAOImplTest {

    @InjectMocks
    private TransactionDAOImpl transactionDAO;

    @Mock
    private DB mockDB = mock(DB.class);

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionDAO = new TransactionDAOImpl(mockDB);
    }

    @Test
    void addTransaction() {
        Transaction transaction = new Transaction(new BigDecimal(100),1, deposit,1,1,1,1);
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = transactionDAO.addTransaction(transaction);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }

    @Test
    void addTransactionSQLExceptionTest() {
        Transaction transaction = new Transaction(new BigDecimal(100),1, deposit,1,1,1,1);
        when(mockDB.update(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> transactionDAO.addTransaction(transaction));
    }

    @Test
    void getAllTransactions() throws SQLException {
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("id")).thenReturn(1, 2);
        when(mockResultSet.getInt("currency_id")).thenReturn(1, 1);
        when(mockResultSet.getString("transaction_type")).thenReturn(String.valueOf(deposit), String.valueOf(transfer));
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        assertEquals(2, transactions.size());
        assertEquals(1, transactions.getFirst().getId());
        assertEquals(1, transactions.getFirst().getCurrencyId());
        assertEquals("deposit", String.valueOf(transactions.getFirst().getTransactionType()));
        assertEquals(2, transactions.get(1).getId());
        assertEquals(1, transactions.get(1).getCurrencyId());
        assertEquals("transfer", String.valueOf(transactions.get(1).getTransactionType()));
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(3)).next();
        verify(mockResultSet, times(2)).getInt("id");
        verify(mockResultSet, times(2)).getInt("currency_id");
        verify(mockResultSet, times(2)).getString("transaction_type");
    }

    @Test
    void getAllTransactionsSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> transactionDAO.getAllTransactions());
    }

    @Test
    void getTransactionsByUserId() throws SQLException {
        int userId = 1;
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(2);
        when(mockResultSet.getString("transaction_type")).thenReturn(String.valueOf(deposit), String.valueOf(transfer));
        Transaction transaction = transactionDAO.getTransactionsByUserId(userId).getFirst();
        assertEquals(2, transaction.getId());
        assertEquals("deposit", String.valueOf(transaction.getTransactionType()));
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getInt("id");
        verify(mockResultSet, times(1)).getString("transaction_type");
    }

    @Test
    void getTransactionsByUserIdSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> transactionDAO.getTransactionsByUserId(anyInt()));
    }

    @Test
    void getTransactionById() throws SQLException {
        int id = 1;
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("currency_id")).thenReturn(1);
        when(mockResultSet.getString("transaction_type")).thenReturn(String.valueOf(deposit), String.valueOf(transfer));
        Transaction transaction = transactionDAO.getTransactionById(id);
        assertEquals(1, transaction.getCurrencyId());
        assertEquals("deposit", String.valueOf(transaction.getTransactionType()));
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getInt("currency_id");
        verify(mockResultSet, times(1)).getString("transaction_type");
    }

    @Test
    void getTransactionByIdSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> transactionDAO.getTransactionById(anyInt()));
    }
}