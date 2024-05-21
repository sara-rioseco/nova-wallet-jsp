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

import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;

import static com.novawallet.model.entity.TransactionType.transfer;
import static com.novawallet.shared.Utils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Disabled
class TransactionDTOTest {

    }

//    Transaction transaction;
//    TransactionDTO transactionDTO;
//    Timestamp creationDate = Timestamp.from(Instant.now());
//
//    @Mock
//    CurrencyDAO mockCurrencyDAO = mock(CurrencyDAOImpl.class);
//
//    @Mock
//    CurrencyService mockCurrencyService = mock(CurrencyServiceImpl.class);
//
//
//    @BeforeEach
//    void setUp() {
//        transaction = new Transaction(0, new BigDecimal(100), 1, TransactionType.deposit, 1, 1, 1, 1, creationDate );
//        transactionDTO = new TransactionDTO(transaction, 1);
//        when(mockCurrencyService.getCurrencyById(anyInt()).getSymbol()).thenReturn("USD");
//    }
//
//    @Test
//    void getId() {
//        assertEquals(0, transactionDTO.getId());
//    }
//
//    @Test
//    void getAmount() {
//        assertEquals("USD $100.00", transactionDTO.getAmount());
//    }
//
//    @Test
//    void getCurrency() {
//        assertEquals("USD", transactionDTO.getCurrency());
//    }
//
//    @Test
//    void getType() {
//        assertEquals(capitalize(String.valueOf(TransactionType.deposit)), transactionDTO.getType());
//    }
//
//    @Test
//    void getSenderUserId() {
//        assertEquals(1, transactionDTO.getSenderUserId());
//    }
//
//    @Test
//    void getSenderAccountId() {
//        assertEquals(1, transactionDTO.getSenderAccountId());
//    }
//
//    @Test
//    void getReceiverUserId() {
//        assertEquals(1, transactionDTO.getReceiverUserId());
//    }
//
//    @Test
//    void getReceiverAccountId() {
//        assertEquals(1, transactionDTO.getReceiverAccountId());
//    }
//
//    @Test
//    void getDate() {
//        assertEquals("On " + formatDate(creationDate) + " at " + formatTime(creationDate), transactionDTO.getDate());
//    }
//
//    @Test
//    void getSymbol() {
//        assertEquals("USD", transactionDTO.getSymbol());
//    }
//
//    @Test
//    void getCurrentUserId() {
//        assertEquals(1, transactionDTO.getCurrentUserId());
//    }
