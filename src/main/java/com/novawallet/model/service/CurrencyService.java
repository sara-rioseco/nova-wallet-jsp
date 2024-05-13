package com.novawallet.model.service;

import com.novawallet.model.entity.Currency;

import java.util.List;

public interface CurrencyService {
    boolean createCurrency(Currency currency);
    Currency getCurrencyById(int id);
    boolean updateCurrency(Currency currency);
    boolean deleteCurrency(int id);
    List<Currency> getAllCurrencies();
}
