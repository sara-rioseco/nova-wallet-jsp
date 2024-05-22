package com.novawallet.model.dto;

import com.novawallet.model.dao.CurrencyDAO;
import com.novawallet.model.dao.impl.CurrencyDAOImpl;
import com.novawallet.model.entity.Contact;
import com.novawallet.model.entity.Currency;
import com.novawallet.model.entity.Transaction;
import com.novawallet.model.entity.TransactionType;
import com.novawallet.model.service.CurrencyService;
import com.novawallet.model.service.impl.CurrencyServiceImpl;
import com.novawallet.shared.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.util.Calendar;
import java.util.Map;

import static com.novawallet.model.entity.TransactionType.transfer;
import static com.novawallet.shared.Utils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionDTOTest {

    Transaction transaction;
    TransactionDTO transactionDTO;
    Timestamp creationDate = Timestamp.from(Instant.now());

    @Mock
    CurrencyDAO mockCurrencyDAO = mock(CurrencyDAOImpl.class);

    @Mock
    CurrencyService mockCurrencyService = mock(CurrencyServiceImpl.class);

    @Mock
    ResultSet mockRs = mock(ResultSet.class);

    @Mock
    Currency mockCurrency = mock(Currency.class);

    @Mock
    DB mockDB = mock(DB.class);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaction = new Transaction(0, new BigDecimal(100), 1, TransactionType.deposit, 1, 1, 1, 1, creationDate);
        transactionDTO = new TransactionDTO();
    }


    @Test
    @Disabled
    void getId() {

        when(mockCurrencyService.getCurrencyById(anyInt())).thenReturn(mockCurrency);
        when(mockCurrency.getSymbol()).thenReturn("USD");
        when(mockCurrencyDAO.getCurrencyById(anyInt())).thenReturn(mockCurrency);
        when(mockDB.query(anyString())).thenReturn(mockRs);

        transactionDTO = new TransactionDTO(transaction, 1, mockDB);

        assertEquals(0, transactionDTO.getId());
    }

    @Test
    void getAmount() {
        assertEquals("", transactionDTO.getAmount());
    }

    @Test
    void getCurrency() {
        assertEquals("", transactionDTO.getCurrency());
    }

    @Test
    void getType() {
        assertEquals("", transactionDTO.getType());
    }

    @Test
    void getSenderUserId() {
        assertEquals(0, transactionDTO.getSenderUserId());
    }

    @Test
    void getSenderAccountId() {
        assertEquals(0, transactionDTO.getSenderAccountId());
    }

    @Test
    void getReceiverUserId() {
        assertEquals(0, transactionDTO.getReceiverUserId());
    }

    @Test
    void getReceiverAccountId() {
        assertEquals(0, transactionDTO.getReceiverAccountId());
    }

    @Test
    void getDate() {
        assertEquals("", transactionDTO.getDate());
    }

    @Test
    void getSymbol() {
        assertEquals("", transactionDTO.getSymbol());
    }

    @Test
    void getCurrentUserId() {
        assertEquals(0, transactionDTO.getCurrentUserId());
    }
}