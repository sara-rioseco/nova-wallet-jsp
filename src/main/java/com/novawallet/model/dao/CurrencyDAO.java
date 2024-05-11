package com.novawallet.model.dao;

import com.novawallet.model.entity.Currency;

import java.util.List;

public interface CurrencyDAO {
    int addCurrency(Currency currency);
    List<Currency> getAllCurrencies();
    Currency getCurrencyBySymbol(String symbol);
    Currency getCurrencyById(int id);
    int updateCurrency(int id, Currency currency);
    int deleteCurrency(int id);
}
