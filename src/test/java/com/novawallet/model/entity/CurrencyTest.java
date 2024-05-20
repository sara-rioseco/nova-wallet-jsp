package com.novawallet.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    Currency currency;

    @BeforeEach
    void setUp() {
        currency = new Currency("Australian Dollars", "AUD");
    }

    @Test
    void getId() {
        assertEquals(0, currency.getId());
    }

    @Test
    void setId() {
        currency.setId(2);
        assertEquals(2, currency.getId());
    }

    @Test
    void getName() {
        assertEquals("Australian Dollars", currency.getName());
    }

    @Test
    void setName() {
        currency.setName("Aussie Dollars");
        assertEquals("Aussie Dollars", currency.getName());
    }

    @Test
    void getSymbol() {
        assertEquals("AUD", currency.getSymbol());
    }

    @Test
    void setSymbol() {
        currency.setSymbol("CAD");
        assertEquals("CAD", currency.getSymbol());
    }
}