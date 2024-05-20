package com.novawallet.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account account;
    Timestamp creationDate;

    @BeforeEach
    void setUp() {
        account = new Account(1,1);
    }

    @Test
    void getId() {
        assertEquals(0,account.getId());
    }

    @Test
    void setId() {
        account.setId(1);
        assertEquals(1,account.getId());
    }

    @Test
    void getOwnerId() {
        assertEquals(1,account.getOwnerId());
    }

    @Test
    void getCurrencyId() {
        assertEquals(1,account.getCurrencyId());
    }

    @Test
    void getBalance() {
        assertEquals(new BigDecimal(0),account.getBalance());
    }

    @Test
    void setBalance() {
        account.setBalance(new BigDecimal(100));
        assertEquals(new BigDecimal(100),account.getBalance());
    }

    @Test
    void addBalance() {
        account.addBalance(new BigDecimal(100));
        assertEquals(new BigDecimal(100),account.getBalance());
    }

    @Test
    void subtractBalance() {
        account.addBalance(new BigDecimal(100));
        account.subtractBalance(new BigDecimal(50));
        assertEquals(new BigDecimal(50),account.getBalance());
    }

    @Test
    void getCreationDate() {
        Account newAccount = new Account(1,1,1, new BigDecimal(0),creationDate);
        assertEquals(creationDate,newAccount.getCreationDate());
    }
}