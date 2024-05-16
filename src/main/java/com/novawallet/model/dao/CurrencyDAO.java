package com.novawallet.model.dao;

import com.novawallet.model.entity.Currency;
import java.util.List;

public interface CurrencyDAO {
    boolean addCurrency(Currency currency);
    List<Currency> getAllCurrencies();
    Currency getCurrencyBySymbol(String symbol);
    Currency getCurrencyById(int id);
    boolean updateCurrency(Currency currency);
    boolean deleteCurrency(int id);
}
