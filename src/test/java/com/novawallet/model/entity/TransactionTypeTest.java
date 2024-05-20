package com.novawallet.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTypeTest {

    @Test
    void testValues() {
        String[] expectedValues = {
                "deposit", "withdrawal", "transfer"
        };
        String[] actualValues = new String[TransactionType.values().length];
        for (int i = 0; i < TransactionType.values().length; i++) {
            actualValues[i] = TransactionType.values()[i].toString();
        }
        assertArrayEquals(expectedValues, actualValues);
    }

    @Test
    void testValueOf() {
        String deposit = "deposit";
        TransactionType expectedDeposit = TransactionType.deposit;
        TransactionType actualDeposit = TransactionType.valueOf(deposit);
        assertEquals(expectedDeposit, actualDeposit);
    }
}