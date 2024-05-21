package com.novawallet.model.dao.impl;

import com.novawallet.model.entity.Currency;
import com.novawallet.shared.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyDAOImplTest {

    @InjectMocks
    private CurrencyDAOImpl currencyDAO;

    @Mock
    private DB mockDB = mock(DB.class);

    @Mock
    private ResultSet mockResultSet;


    @BeforeEach
    void setUp() {
        currencyDAO = spy(new CurrencyDAOImpl());
        currencyDAO.stmt = mockDB.getStatement();

    }

    @Test
    void testAddCurrency() throws SQLException {
        Currency currency = new Currency(1, "Dollar", "$");

        OngoingStubbing<Integer> integerOngoingStubbing = when(currencyDAO.update(anyString())).thenReturn(1);

        boolean result = currencyDAO.addCurrency(currency);

        assertTrue(result);
        verify(currencyDAO).update(anyString());
    }

    @Test
    void getAllCurrencies() {
    }

    @Test
    void getCurrencyBySymbol() {
    }

    @Test
    void getCurrencyById() {
    }

    @Test
    void updateCurrency() {
    }

    @Test
    void deleteCurrency() {
    }
}