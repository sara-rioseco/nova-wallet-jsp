package com.novawallet.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    Transaction transaction;
    Timestamp creationDate = Timestamp.from(Instant.now());

    @BeforeEach
    void setUp() {
        transaction = new Transaction(new BigDecimal(100), 1, TransactionType.deposit, 1, 1, 2, 2);
    }

    @Test
    void getId() {
        assertEquals(0, transaction.getId());
    }

    @Test
    void setId() {
        transaction.setId(2);
        assertEquals(2, transaction.getId());
    }

    @Test
    void getAmount() {
        assertEquals(new BigDecimal(100), transaction.getAmount());
    }

    @Test
    void setAmount() {
        transaction.setAmount(new BigDecimal(200));
        assertEquals(new BigDecimal(200), transaction.getAmount());
    }

    @Test
    void getCurrencyId() {
        assertEquals(1, transaction.getCurrencyId());
    }

    @Test
    void setCurrencyId() {
        transaction.setCurrencyId(2);
        assertEquals(2, transaction.getCurrencyId());
    }

    @Test
    void getTransactionType() {
        assertEquals(TransactionType.deposit, transaction.getTransactionType());
    }

    @Test
    void setTransactionType() {
        transaction.setTransactionType(TransactionType.transfer);
        assertEquals(TransactionType.transfer, transaction.getTransactionType());
    }

    @Test
    void getSenderUserId() {
        assertEquals(1, transaction.getSenderUserId());
    }

    @Test
    void setSenderUserId() {
        transaction.setSenderUserId(2);
        assertEquals(2, transaction.getSenderUserId());
    }

    @Test
    void getSenderAccountId() {
        assertEquals(1, transaction.getSenderAccountId());
    }

    @Test
    void setSenderAccountId() {
        transaction.setSenderAccountId(2);
    }

    @Test
    void getReceiverAccountId() {
        assertEquals(2, transaction.getReceiverAccountId());
    }

    @Test
    void setReceiverAccountId() {
        transaction.setReceiverAccountId(3);
        assertEquals(3, transaction.getReceiverAccountId());
    }

    @Test
    void getReceiverUserId() {
        assertEquals(2, transaction.getReceiverUserId());
    }

    @Test
    void setReceiverUserId() {
        transaction.setReceiverUserId(3);
        assertEquals(3, transaction.getReceiverUserId());
    }

    @Test
    void getCreationDate() {
        Transaction newTransaction = new Transaction(0, new BigDecimal(100), 1, TransactionType.deposit, 1, 1, 2, 2, creationDate);
        assertEquals(creationDate, newTransaction.getCreationDate());
    }
}