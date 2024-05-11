package com.novawallet.model.dao.impl;

import com.novawallet.model.dao.CurrencyDAO;
import com.novawallet.model.entity.Currency;
import com.novawallet.shared.DB;

import java.util.List;

public class CurrencyDAOImpl extends DB implements CurrencyDAO {

    public CurrencyDAOImpl() {
        this.connect();
    }

    @Override
    public int addCurrency(Currency currency) {
        return 0;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return List.of();
    }

    @Override
    public Currency getCurrencyBySymbol(String symbol) {
        return null;
    }

    @Override
    public Currency getCurrencyById(int id) {
        return null;
    }

    @Override
    public int updateCurrency(int id, Currency currency) {
        return 0;
    }

    @Override
    public int deleteCurrency(int id) {
        return 0;
    }
}
