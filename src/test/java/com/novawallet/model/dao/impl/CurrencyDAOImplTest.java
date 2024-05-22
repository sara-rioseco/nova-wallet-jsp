package com.novawallet.model.dao.impl;

import com.novawallet.model.entity.Currency;
import com.novawallet.shared.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
        MockitoAnnotations.openMocks(this);
        currencyDAO = new CurrencyDAOImpl(mockDB);
    }

    @Test
    void testAddCurrency() {
        Currency currency = new Currency("Dollar", "USD");
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = currencyDAO.addCurrency(currency);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }

    @Test
    void addCurrencySQLExceptionTest() {
        Currency currency = new Currency("Dollar", "USD");
        when(mockDB.update(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> currencyDAO.addCurrency(currency));
    }

    @Test
    void testGetAllCurrencies() throws SQLException {
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("name")).thenReturn("Dollar", "Euro");
        when(mockResultSet.getString("symbol")).thenReturn("USD", "EUR");
        List<Currency> currencies = currencyDAO.getAllCurrencies();
        assertEquals(2, currencies.size());
        assertEquals("Dollar", currencies.get(0).getName());
        assertEquals("USD", currencies.get(0).getSymbol());
        assertEquals("Euro", currencies.get(1).getName());
        assertEquals("EUR", currencies.get(1).getSymbol());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(3)).next();  // 3 times: true, true, false
        verify(mockResultSet, times(2)).getString("name");
        verify(mockResultSet, times(2)).getString("symbol");
    }

    @Test
    void getAllCurrenciesSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> currencyDAO.getAllCurrencies());
    }

    @Test
    void testGetCurrencyBySymbol() throws SQLException {
        String symbol = "USD";
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Dollar");
        Currency currency = currencyDAO.getCurrencyBySymbol(symbol);
        assertEquals(1, currency.getId());
        assertEquals("Dollar", currency.getName());
        assertEquals("USD", currency.getSymbol());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getInt("id");
        verify(mockResultSet, times(1)).getString("name");
    }

    @Test
    void getCurrencyBySymbolSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> currencyDAO.getCurrencyBySymbol(anyString()));
    }

    @Test
    void testGetCurrencyById() throws SQLException {
        int id = 1;
        when(mockDB.query(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("name")).thenReturn("Dollar");
        when(mockResultSet.getString("symbol")).thenReturn("USD");
        Currency currency = currencyDAO.getCurrencyById(id);
        assertEquals(1, currency.getId());
        assertEquals("Dollar", currency.getName());
        assertEquals("USD", currency.getSymbol());
        verify(mockDB, times(1)).query(anyString());
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getString("name");
        verify(mockResultSet, times(1)).getString("symbol");
    }

    @Test
    void getCurrencyByIdSQLExceptionTest() {
        when(mockDB.query(anyString())).thenThrow(new RuntimeException("SQL error"));
        assertThrows(RuntimeException.class, () -> currencyDAO.getCurrencyById(anyInt()));
    }

    @Test
    void testUpdateCurrency() {
        Currency currency = new Currency(1, "Euro", "EUR");
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = currencyDAO.updateCurrency(currency);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }

    @Test
    void testDeleteCurrency() {
        int id = 1;
        when(mockDB.update(anyString())).thenReturn(1);
        boolean result = currencyDAO.deleteCurrency(id);
        assertTrue(result);
        verify(mockDB, times(1)).update(anyString());
    }
}