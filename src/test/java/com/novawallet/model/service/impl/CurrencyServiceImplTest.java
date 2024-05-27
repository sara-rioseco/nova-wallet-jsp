package com.novawallet.model.service.impl;

import com.novawallet.model.dao.CurrencyDAO;
import com.novawallet.model.entity.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyServiceImplTest {

    @Mock
    private CurrencyDAO currencyDAO;

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCurrencySuccess() {
        Currency currency = new Currency(1, "Dollar", "USD");
        when(currencyDAO.getCurrencyBySymbol("USD")).thenReturn(null);
        when(currencyDAO.addCurrency(currency)).thenReturn(true);
        boolean result = currencyService.createCurrency(currency);
        assertTrue(result);
        verify(currencyDAO).getCurrencyBySymbol("USD");
        verify(currencyDAO).addCurrency(currency);
    }

    @Test
    void testCreateCurrencyFailure() {
        Currency currency = new Currency(1, "Dollar", "USD");
        when(currencyDAO.getCurrencyBySymbol("USD")).thenReturn(currency);
        boolean result = currencyService.createCurrency(currency);
        assertFalse(result);
        verify(currencyDAO).getCurrencyBySymbol("USD");
        verify(currencyDAO, never()).addCurrency(currency);
    }

    @Test
    void testGetCurrencyById() {
        Currency currency = new Currency(1, "Dollar", "USD");
        when(currencyDAO.getCurrencyById(1)).thenReturn(currency);
        Currency result = currencyService.getCurrencyById(1);
        assertNotNull(result);
        assertEquals(currency, result);
        verify(currencyDAO).getCurrencyById(1);
    }

    @Test
    void testUpdateCurrencySuccess() {
        Currency currency = new Currency(1, "Dollar", "USD");
        when(currencyDAO.getCurrencyById(1)).thenReturn(currency);
        when(currencyDAO.updateCurrency(currency)).thenReturn(true);
        boolean result = currencyService.updateCurrency(currency);
        assertTrue(result);
        verify(currencyDAO).getCurrencyById(1);
        verify(currencyDAO).updateCurrency(currency);
    }
    @Test
    void testUpdateCurrencyFailure() {
        Currency currency = new Currency(1, "Dollar", "USD");
        when(currencyDAO.getCurrencyById(1)).thenReturn(null);
        boolean result = currencyService.updateCurrency(currency);
        assertFalse(result);
        verify(currencyDAO).getCurrencyById(1);
        verify(currencyDAO, never()).updateCurrency(currency);
    }

    @Test
    void testDeleteCurrencySuccess() {
        when(currencyDAO.getCurrencyById(1)).thenReturn(new Currency(1, "Dollar", "USD"));
        when(currencyDAO.deleteCurrency(1)).thenReturn(true);

        boolean result = currencyService.deleteCurrency(1);

        assertTrue(result);
        verify(currencyDAO).getCurrencyById(1);
        verify(currencyDAO).deleteCurrency(1);
    }

    @Test
    void testDeleteCurrencyFailure() {
        when(currencyDAO.getCurrencyById(1)).thenReturn(null);
        boolean result = currencyService.deleteCurrency(1);
        assertFalse(result);
        verify(currencyDAO).getCurrencyById(1);
        verify(currencyDAO, never()).deleteCurrency(1);
    }

    @Test
    void testGetAllCurrencies() {
        List<Currency> currencies = Arrays.asList(
                new Currency(1, "Dollar", "USD"),
                new Currency(2, "Euro", "EUR")
        );
        when(currencyDAO.getAllCurrencies()).thenReturn(currencies);
        List<Currency> result = currencyService.getAllCurrencies();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(currencies, result);
        verify(currencyDAO).getAllCurrencies();
    }
}